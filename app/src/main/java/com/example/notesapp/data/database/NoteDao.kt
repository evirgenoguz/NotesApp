package com.example.notesapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notesapp.data.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    //Insertin onConflict Methodunu Replace yaparsak aslinda update te etmis oluyoruz
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM notes ORDER BY noteId DESC")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE noteTitle LIKE '%'||:searchQuery||'%'")
    fun searchInNotesTitle(searchQuery: String): Flow<List<Note>>

    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()

    @Query("SELECT noteLabel FROM notes WHERE noteLabel IS NOT NULL")
    fun getNotesLabels(): Flow<List<String>>

    @Query("SELECT * FROM notes WHERE noteLabel = :noteLabel")
    fun getSpecificLabeledNotes(noteLabel: String): Flow<List<Note>>



}