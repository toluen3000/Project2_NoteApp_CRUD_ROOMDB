package com.example.project2_noteapp_crud

import activities.AddEditActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.project2_noteapp_crud.Constant.Constant
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

@SuppressLint("StaticFieldLeak")

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerNote :RecyclerView
    private lateinit var btnAddNote :FloatingActionButton
    @Suppress("UNCHECKED_CAST")
    private val factory:ViewModelProvider.Factory = object :ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NoteViewModel(application) as T
        }
    }
    private lateinit var getResult: ActivityResultLauncher<Intent>
    private val noteViewModel: NoteViewModel by viewModels { factory }

    //shared preference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //ánh xạ
        btnAddNote = findViewById(R.id.btnAddNote)
        recyclerNote = findViewById(R.id.recycler_Note)

        // khai báo adapter
        val adapterNote = NoteAdapter()
        recyclerNote.adapter = adapterNote
        recyclerNote.setHasFixedSize(true)
        //hiển thị theo grid
        recyclerNote.layoutManager = GridLayoutManager(this,2,GridLayoutManager.HORIZONTAL,false)

//        //noteViewModel
//        noteViewModel = ViewModelProvider(this,
//            ViewModelProvider.AndroidViewModelFactory(application)
//        )[NoteViewModel::class.java]

        noteViewModel.allNotesVM.observe(this){
            // add data to recycler view
            adapterNote.setNote(it)

        }

        val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == Constant.REQUEST_CODE) {
                val title = it.data?.getStringExtra(Constant.EXTRA_TITLE)
                val description = it.data?.getStringExtra(Constant.EXTRA_DESCRIPTION)
                val nums = it.data?.getIntExtra(Constant.EXTRA_NUMS,-1)

                val note = Note(title!!, description!!,nums!!)
                noteViewModel.addNote(note)
            }
        }
        btnAddNote.setOnClickListener{
            val intent = Intent(this@MainActivity,AddEditActivity::class.java)
            getResult.launch(intent) }


    }
}