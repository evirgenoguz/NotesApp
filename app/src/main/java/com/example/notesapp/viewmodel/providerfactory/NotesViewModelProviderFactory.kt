package com.example.notesapp.viewmodel.providerfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.data.repositories.NoteRepository
import com.example.notesapp.viewmodel.NotesViewModel

class NotesViewModelProviderFactory(
    private val noteRepository: NoteRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesViewModel(noteRepository) as T
    }
}