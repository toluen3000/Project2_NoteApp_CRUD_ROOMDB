package com.example.project2_noteapp_crud

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDataBase:RoomDatabase() {

    abstract fun getNoteDAO():NoteDAO

    companion object {
        private var INSTANT: NoteDataBase? = null

        fun getInstance(context: Context):NoteDataBase{
            //nếu INSTANT là null, sau đó trả về nó
            // nếu là nó thì tạo database
            return INSTANT ?: synchronized(this){ // để cho dữ liệu truy nhập vào cùng 1 nguồn
                val instance = Room.databaseBuilder(context.applicationContext, NoteDataBase::class.java, "note_database")
                    .build()
                INSTANT = instance
                //return instance
                instance
            }
        }
    }
}