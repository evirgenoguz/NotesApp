package com.example.notesapp.adapters

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.data.Note
import com.example.notesapp.databinding.LabeledNotesItemCardBinding
import com.example.notesapp.ui.LabeledNotesFragment
import com.example.notesapp.ui.LabeledNotesFragmentDirections

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

        //Todo()
        //Burdan action ile tvnoteslabeldaki labeli alip notesListFragmenta gondericem
        //Burdan alinan labeli search ile araticam ya da direkt ordan tiklayarak gittigimde
        //Bu labella ilgili seyler gelicek
        // yada search kismini label:deneme seklinde aratip boyle bir tag varsa
        //viewmodeldan data katmanina kadar bunu bu sekilde aratabilirim
        // su an hazir olan methoda ekleme yaparak

        holder.itemView.setOnClickListener {
            onClick?.invoke(label)
        }



    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    var onClick: ((String) -> Unit)? = null

}