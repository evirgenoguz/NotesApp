package com.example.notesapp.data.repositories

import com.example.notesapp.data.model.Note
import com.example.notesapp.data.database.NotesDatabase

class NoteRepository(
    notesDatabase: NotesDatabase
) {

    val notesDao = notesDatabase.noteDao

    suspend fun upsertNote(note: Note) = notesDao.upsertNote(note)

    suspend fun deleteNote(note: Note) = notesDao.deleteNote(note)

    fun getAllNotes() = notesDao.getAllNotes()

    fun getSpecificLabeledNotes(noteLabel: String) = notesDao.getSpecificLabeledNotes(noteLabel)

    fun searchNotes(searchQuery: String) = notesDao.searchInNotesTitle(searchQuery)

    suspend fun deleteAllNotes() = notesDao.deleteAllNotes()

    fun getNotesLabel() = notesDao.getNotesLabels()

}