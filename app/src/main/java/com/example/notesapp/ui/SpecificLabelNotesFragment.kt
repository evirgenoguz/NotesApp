package com.example.notesapp.ui

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentNotesListBinding
import com.example.notesapp.databinding.FragmentSpecificLabelNotesBinding
import com.example.notesapp.ui.adapters.NotesListAdapter
import com.example.notesapp.viewmodel.NotesViewModel


class SpecificLabelNotesFragment : Fragment() {

    private var _binding: FragmentSpecificLabelNotesBinding? = null
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
        _binding = FragmentSpecificLabelNotesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        lifecycleScope.launchWhenStarted {
            viewModel.specificLabeledNotes.collect { specificLabeledNotes ->
                notesListAdapter.differ.submitList(specificLabeledNotes)
            }
        }

        viewModel.label?.let {
            Log.d("selam2", it)
            viewModel.getSpecificLabeledNotes(it)
            lifecycleScope.launchWhenStarted {
                viewModel.specificLabeledNotes.collect { specificLabeledNotes ->
                    notesListAdapter.differ.submitList(specificLabeledNotes)
                }
            }
            binding.tvLabel.text = "\t" + it
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