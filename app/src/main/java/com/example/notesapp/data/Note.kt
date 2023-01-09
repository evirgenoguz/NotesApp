package com.example.notesapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val noteId: Int = 0,
    val noteTitle: String,
    val noteBody: String
)
