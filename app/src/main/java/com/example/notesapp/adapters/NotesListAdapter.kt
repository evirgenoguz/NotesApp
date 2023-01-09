package com.example.notesapp.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.NoteItemCardBinding

class NotesListAdapter: RecyclerView.Adapter<NotesListAdapter.NoteListViewHolder>() {



    inner class NoteListViewHolder(val binding: NoteItemCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}