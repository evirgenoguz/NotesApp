package com.example.notesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.notesapp.R
import com.example.notesapp.adapters.NotesListAdapter
import com.example.notesapp.data.Note
import com.example.notesapp.databinding.FragmentNotesListBinding
import com.example.notesapp.viewmodel.NotesViewModel
import kotlinx.coroutines.flow.collect


class NotesListFragment : Fragment() {

    private var _binding : FragmentNotesListBinding? = null
    private val binding get() = _binding!!
    private lateinit var notesListadapter: NotesListAdapter
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
                notesListadapter.differ.submitList(notesList)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.searchNotes.collect { searchedNotes ->
                notesListadapter.differ.submitList(searchedNotes)
            }
        }

        binding.edSearch.addTextChangedListener {
            viewModel.searchNotes(it.toString().trim())
        }

        binding.fabAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_notesListFragment_to_noteFragment)
        }

        notesListadapter.onClick = { note ->
            val bundle = Bundle().apply {
                putParcelable("note", note)
            }

            findNavController().navigate(R.id.action_notesListFragment_to_noteFragment, bundle)
        }

    }

    private fun setupRecyclerView() {
        notesListadapter = NotesListAdapter()
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesListadapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}