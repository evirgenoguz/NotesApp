package com.example.notesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.Note
import com.example.notesapp.repositories.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NotesViewModel(
    private val notesRepository: NoteRepository
): ViewModel() {

    private val _searchNotes = MutableStateFlow<List<Note>>(emptyList())
    val searchNotes: StateFlow<List<Note>> = _searchNotes
    val notes = notesRepository.getAllNotes()
    val notesLabels = notesRepository.getNotesLabel()

    fun upsertNote(note: Note) = viewModelScope.launch {
        notesRepository.upsertNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        notesRepository.deleteNote(note)
    }

    fun deleteAllNotes() = viewModelScope.launch {
        notesRepository.deleteAllNotes()
    }

    fun searchNotes(searchQuery: String) = viewModelScope.launch {
        notesRepository.searchNotes(searchQuery).collect { notesList ->
            _searchNotes.emit(notesList)
        }
    }


}