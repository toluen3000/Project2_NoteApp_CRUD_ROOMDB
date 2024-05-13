package com.example.project2_noteapp_crud

import androidx.lifecycle.LiveData
// kho lưu trữ trừu tượng hoá từ NOTEDAO
class NoteRepository(private val noteDAO: NoteDAO) {
    val allNotes: LiveData<MutableList<Note>> =  noteDAO.getAllNote()

    //viết code cho các phương thức ở INTERFACE noteDao
    suspend fun insertNote(note: Note){
        noteDAO.insert(note)
    }

    suspend fun deleteNote(note: Note){
        noteDAO.delete(note)
    }
    suspend fun updateNote(note: Note){
        noteDAO.update(note)
    }

}