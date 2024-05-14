package com.example.project2_noteapp_crud

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//
class NoteAdapter( var noteList: MutableList<Note>):RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    //tạo viewHolder
    inner class NoteViewHolder(view:View):RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view1 = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_notes,parent,false)
        return  NoteViewHolder(view1)
    }

    override fun getItemCount(): Int {
        return  noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        holder.itemView.apply {
            var txtTile  = findViewById<TextView>(R.id.txtTitle)
            var txtNums = findViewById<TextView>(R.id.txtNums)
            var txtDescription = findViewById<TextView>(R.id.txtDescription)
            var note = noteList[position]
            txtNums.text = note.nums.toString()
            txtTile.text = note.title
            txtDescription.text = note.description
        }
    }



}
