package activities

import android.content.ClipData.Item
import android.content.Intent
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project2_noteapp_crud.Constant.Constant
import com.example.project2_noteapp_crud.MainActivity
import com.example.project2_noteapp_crud.R

class AddEditActivity : AppCompatActivity() {
    private lateinit var edtTitle: EditText
    private lateinit var edtDescription : EditText
    private lateinit var numberPicker: NumberPicker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_edit)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        edtTitle = findViewById(R.id.edtTitle)
        edtDescription = findViewById(R.id.edtDescription)
        numberPicker = findViewById(R.id.numsPicker)
        numberPicker.minValue = 1
        numberPicker.maxValue = 20

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_close_24)
            title = "ADD NOTE"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.saveMenu -> {
                saveNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveNote() {
        val intentI = Intent(this,MainActivity::class.java)
        var title = edtTitle.text.toString()
        var description = edtDescription.text.toString()
        var numberPicker = numberPicker.value

        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this,"Please insert Title and Description",Toast.LENGTH_SHORT).show()
        }

        val bundle = Bundle()
        bundle.putString(Constant.EXTRA_TITLE, title )
        bundle.putString(Constant.EXTRA_DESCRIPTION,description)
        bundle.putInt(Constant.EXTRA_NUMS,numberPicker)
        // gói
        intentI.putExtras(bundle)
        startActivity(intentI)

    }
}