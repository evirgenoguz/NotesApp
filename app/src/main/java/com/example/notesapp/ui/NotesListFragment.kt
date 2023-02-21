package com.example.notesapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.R
import com.example.notesapp.ui.adapters.NotesListAdapter
import com.example.notesapp.databinding.FragmentNotesListBinding
import com.example.notesapp.viewmodel.NotesViewModel


class NotesListFragment : Fragment() {

    private var _binding: FragmentNotesListBinding? = null
    private val binding get() = _binding!!
    private lateinit var notesListAdapter: NotesListAdapter
    private lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()


        lifecycleScope.launchWhenStarted {
            viewModel.notes.collect { notesList ->
                notesListAdapter.differ.submitList(notesList)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.searchNotes.collect { searchedNotes ->
                notesListAdapter.differ.submitList(searchedNotes)
            }
        }

        binding.edSearch.addTextChangedListener {
            viewModel.searchNotes(it.toString().trim())
        }

        binding.fabAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_notesListFragment_to_noteFragment)
        }

        notesListAdapter.onClick = { note ->
            val bundle = Bundle().apply {
                putParcelable("note", note)
            }
            findNavController().navigate(R.id.action_notesListFragment_to_noteFragment, bundle)
        }


        viewModel.label?.let {
            Log.d("selam2", it)
            viewModel.getSpecificLabeledNotes(it)
            lifecycleScope.launchWhenStarted {
                viewModel.specificLabeledNotes.collect { specificLabeledNotes ->
                    notesListAdapter.differ.submitList(specificLabeledNotes)
                }
            }
            viewModel.label = null
        }






    }

    private fun setupRecyclerView() {
        notesListAdapter = NotesListAdapter()
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesListAdapter
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}