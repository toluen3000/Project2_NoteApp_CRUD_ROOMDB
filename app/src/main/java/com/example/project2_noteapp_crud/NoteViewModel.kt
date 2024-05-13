package com.example.project2_noteapp_crud

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NoteViewModel(application: Application):AndroidViewModel(application) {
    val allNotesVM : LiveData<MutableList<Note>>
    val repositoryVM: NoteRepository

    init {
        val dao = NoteDataBase.getInstance(application).getNoteDAO()
        repositoryVM = NoteRepository(dao)
        allNotesVM = repositoryVM.allNotes
    }

    fun deleteNoteVM(note: Note) = viewModelScope.launch {
        repositoryVM.deleteNote(note)
    }
    fun updateNoteVM(note: Note) = viewModelScope.launch {
        repositoryVM.updateNote(note)
    }
    fun addNote(note: Note) = viewModelScope.launch {
        repositoryVM.insertNote(note)
    }


}