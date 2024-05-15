package com.example.project2_noteapp_crud

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


//
class NoteAdapter( ):RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private lateinit var noteList: MutableList<Note>
    //tạo viewHolder
    inner class NoteViewHolder(view:View):RecyclerView.ViewHolder(view)
    private val differCallback = object :DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.title == newItem.title &&
                    oldItem.description == newItem.description
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view1 = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_notes,parent,false)
        return  NoteViewHolder(view1)
    }

    override fun getItemCount(): Int {
        return  noteList.size
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        holder.itemView.apply {
            var txtTile  = findViewById<TextView>(R.id.txtTitle)
            var txtDescription = findViewById<TextView>(R.id.txtDescription)
            var note = noteList[position]
            txtTile.text = note.title
            txtDescription.text = note.description
        }
    }


    fun setNote(note:MutableList<Note>){
        noteList = note
    }


}
