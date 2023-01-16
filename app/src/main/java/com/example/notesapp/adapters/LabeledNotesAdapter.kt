package com.example.notesapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.Note
import com.example.notesapp.databinding.LabeledNotesItemCardBinding

class LabeledNotesAdapter: RecyclerView.Adapter<LabeledNotesAdapter.LabeledNotesViewHolder>() {

    inner class LabeledNotesViewHolder(private val binding: LabeledNotesItemCardBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(label: String) {
            binding.tvNotesLabel.text = label
        }
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabeledNotesViewHolder {
        return LabeledNotesViewHolder(
            LabeledNotesItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: LabeledNotesViewHolder, position: Int) {
        val label = differ.currentList[position]
        holder.bind(label)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}