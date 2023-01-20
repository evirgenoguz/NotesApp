package com.example.notesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.R
import com.example.notesapp.adapters.LabeledNotesAdapter
import com.example.notesapp.adapters.NotesListAdapter
import com.example.notesapp.databinding.FragmentLabeledNotesBinding
import com.example.notesapp.viewmodel.NotesViewModel
import kotlinx.coroutines.flow.collect


class LabeledNotesFragment : Fragment() {

    private var _binding: FragmentLabeledNotesBinding? = null
    private val binding get() = _binding!!

    private lateinit var labeledNotesAdapter: LabeledNotesAdapter
    private lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLabeledNotesBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        lifecycleScope.launchWhenStarted {
            viewModel.notesLabels.collect{ labels ->
                labeledNotesAdapter.differ.submitList(labels)
            }
        }

        clickedFabAddNote()

        labeledNotesAdapter.onClick = { label ->
            val bundle = Bundle().apply {
                putString("label", label)
            }
            findNavController().navigate(R.id.action_labeledNotesFragment_to_notesListFragment, bundle)
        }


    }



    private fun clickedFabAddNote() {
        binding.fabAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_labeledNotesFragment_to_noteFragment)
        }
    }

    private fun setupRecyclerView() {
        labeledNotesAdapter = LabeledNotesAdapter()
        binding.rvLabels.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = labeledNotesAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}