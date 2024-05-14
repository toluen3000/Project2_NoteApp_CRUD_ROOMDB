package com.example.project2_noteapp_crud

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project2_noteapp_crud.databinding.ActivityMainBinding
import java.util.prefs.Preferences

@SuppressLint("StaticFieldLeak")
private lateinit var binding :ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var noteViewModel: NoteViewModel
    //shared preference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //khai báo list
        var listNotes = mutableListOf<Note>()
        // khai báo adapter
        val adapterNote = NoteAdapter(listNotes)
        binding.recyclerNote.adapter = adapterNote
        //hiển thị theo grid
        binding.recyclerNote.layoutManager = GridLayoutManager(this,2,GridLayoutManager.HORIZONTAL,false)

        //noteViewModel
        noteViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))[NoteViewModel::class.java]
        noteViewModel.allNotesVM.observe(this){
            // add data to recycler view
            adapterNote.noteList
        }


    }
}