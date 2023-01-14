package com.example.notesapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val noteId: Int = 0,
    val noteTitle: String,
    val noteBody: String,
    val noteLabel: String? = "default"
): Parcelable
