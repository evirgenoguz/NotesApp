package com.example.notesapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.Note
import com.example.notesapp.databinding.LabeledNotesItemCardBinding

class LabeledNotesAdapter: RecyclerView.Adapter<LabeledNotesAdapter.LabeledNotesViewHolder>() {

    inner class LabeledNotesViewHolder( val binding: LabeledNotesItemCardBinding): RecyclerView.ViewHolder(binding.root){
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

        holder.itemView.rootView.setOnClickListener {
            //Todo()
            //Burdan action ile tvnoteslabeldaki labeli alip notesListFragmenta gondericem
            //Burdan alinan labeli search ile araticam ya da direkt ordan tiklayarak gittigimde
            //Bu labella ilgili seyler gelicek
            // yada search kismini label:deneme seklinde aratip boyle bir tag varsa
            //viewmodeldan data katmanina kadar bunu bu sekilde aratabilirim
            // su an hazir olan methoda ekleme yaparak
            Log.d("selam", holder.binding.tvNotesLabel.text.toString())
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }



}