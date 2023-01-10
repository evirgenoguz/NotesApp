package com.example.notesapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.Note
import com.example.notesapp.databinding.NoteItemCardBinding

class NotesListAdapter: RecyclerView.Adapter<NotesListAdapter.NoteListViewHolder>() {

    inner class NoteListViewHolder(private val binding: NoteItemCardBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(note: Note) {
            binding.tvNoteTitle.text = note.noteTitle
            binding.tvNoteBody.text = note.noteBody
        }

    }

    private val diffCallback = object : DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(p0: Note, p1: Note): Boolean {
            return p0.noteId == p1.noteId
        }

        override fun areContentsTheSame(p0: Note, p1: Note): Boolean {
            return p0 == p1
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        return NoteListViewHolder(
            NoteItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        val note = differ.currentList[position]
        holder.bind(note)

        holder.itemView.setOnClickListener {
            onClick?.invoke(note)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    var onClick: ((Note) -> Unit)? = null
}