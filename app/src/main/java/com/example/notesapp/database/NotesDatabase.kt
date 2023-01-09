package com.example.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.data.Note

@Database(entities = [Note::class], version = 1)
abstract class NotesDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {
        @Volatile
        var INSTANCE: NotesDatabase? = null

        @Synchronized
        fun getDatabaseInstance(context: Context): NotesDatabase {
            if (INSTANCE == null){
                INSTANCE =  Room.databaseBuilder(
                    context,
                    NotesDatabase::class.java,
                    "notesDb.db"
                ).fallbackToDestructiveMigration().build()
            }
            return INSTANCE!!
        }
    }

}