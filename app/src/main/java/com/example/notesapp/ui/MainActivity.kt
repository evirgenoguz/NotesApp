package com.example.notesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.R
import com.example.notesapp.database.NotesDatabase
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.repositories.NoteRepository
import com.example.notesapp.viewmodel.NotesViewModel
import com.example.notesapp.viewmodel.providerfactory.NotesViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val viewModel: NotesViewModel by lazy {
        val database = NotesDatabase.getDatabaseInstance(this)
        val noteRepository = NoteRepository(database)
        val notesProviderFactory = NotesViewModelProviderFactory(noteRepository)
        ViewModelProvider(this, notesProviderFactory)[NotesViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)






    }
}