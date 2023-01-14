package com.example.notesapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.R
import com.example.notesapp.data.Note
import com.example.notesapp.databinding.FragmentNoteBinding
import com.example.notesapp.viewmodel.NotesViewModel


class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: NotesViewModel

    val args by navArgs<NoteFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.note?.let {
            binding.apply {
                etTitle.setText(it.noteTitle)
                etNote.setText(it.noteBody)
            }
            binding.imgDeleteNote.visibility = View.VISIBLE
        }

        binding.apply {
            btnSaveNote.setOnClickListener {
                val id = args.note?.noteId ?: 0
                val noteTitle = etTitle.text.toString()
                val noteText = etNote.text.toString()
                var noteLabel: String = "default"
                etNoteLabel.text.toString()?.let {
                    noteLabel = it
                }

                Note(id, noteTitle, noteText, noteLabel).also { note ->
                    if (noteTitle.isEmpty() && noteText.isEmpty()) {
                        Toast.makeText(context, "All field must be filled", Toast.LENGTH_SHORT)
                            .show()
                        return@setOnClickListener
                    }
                    viewModel.upsertNote(note)
                    findNavController().navigateUp()
                }
            }
        }

        binding.apply {
            imgDeleteNote.setOnClickListener {
                val noteId = args.note!!.noteId
                val noteTitle = etTitle.text.toString()
                val noteText = etNote.text.toString()
                Note(noteId, noteTitle, noteText).also { note ->
                    viewModel.deleteNote(note)
                    findNavController().navigateUp()
                }
            }
        }

        binding.apply {
            imgAddLabel.setOnClickListener {
                if (etNoteLabel.visibility == View.VISIBLE) {
                    etNoteLabel.visibility = View.INVISIBLE
                } else {
                    etNoteLabel.visibility = View.VISIBLE
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}