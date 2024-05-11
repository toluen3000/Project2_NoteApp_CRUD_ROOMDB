package com.example.project2_noteapp_crud

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project2_noteapp_crud.databinding.ActivityMainBinding
import java.util.prefs.Preferences

@SuppressLint("StaticFieldLeak")
private lateinit var binding :ActivityMainBinding
class MainActivity : AppCompatActivity() {
    //shared preference
    private lateinit var mySharePreferences: SharedPreferences
    private val PREF_NAME = "myPref"
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

        binding.btnSave.setOnClickListener {
            mySharePreferences = getSharedPreferences(PREF_NAME,0)
            binding.textView.setText(binding.editTextText.text.toString())
            val editor = mySharePreferences.edit()
            editor.putString("message", binding.editTextText.text.toString())
            editor.commit()
        }
        val prefs = getSharedPreferences(PREF_NAME,0)
        if (prefs.contains("message")){
            val message = prefs.getString("message","not found")
            binding.textView.text = message
        }

    }
}