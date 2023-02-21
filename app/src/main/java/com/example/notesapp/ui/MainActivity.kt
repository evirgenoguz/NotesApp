package com.example.notesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.notesapp.R
import com.example.notesapp.data.database.NotesDatabase
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.data.repositories.NoteRepository
import com.example.notesapp.viewmodel.NotesViewModel
import com.example.notesapp.viewmodel.providerfactory.NotesViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

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

        navController = Navigation.findNavController(this, R.id.host_fragment)
        NavigationUI.setupWithNavController(binding.bottomNavMenu, navController)


    }
}