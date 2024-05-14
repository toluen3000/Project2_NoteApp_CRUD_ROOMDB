package com.example.project2_noteapp_crud
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note_table")
data class Note (    val title: String,
                     val description: String,
                    val nums : Int){
    @PrimaryKey(autoGenerate = true)
    val id = 0

}