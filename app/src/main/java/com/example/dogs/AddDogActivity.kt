package com.example.dogs

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.ComponentActivity

class AddDogActivity : ComponentActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextAge: EditText
    private lateinit var editTextType: EditText
    private lateinit var editTextOwnerName: EditText

    private var position: Int = -1


    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_dog)

        editTextName = findViewById(R.id.editTextName)
        editTextAge = findViewById(R.id.editTextAge)
        editTextType = findViewById(R.id.editTextType)
        editTextOwnerName = findViewById(R.id.editTextOwnerName)

        btnSave = findViewById(R.id.btnSave)

        intent?.let {
            editTextName.setText(it.getStringExtra("name"))
            editTextAge.setText(it.getStringExtra("age"))
            editTextType.setText(it.getStringExtra("type"))
            editTextOwnerName.setText(it.getStringExtra("owner"))
            position = it.getIntExtra("position", -1)

        }

        btnSave.setOnClickListener {
            val name = editTextName.text.toString()
            val age = editTextAge.text.toString()
            val type = editTextType.text.toString()
            val owner = editTextOwnerName.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra("name", name)
            resultIntent.putExtra("age", age)
            resultIntent.putExtra("type", type)
            resultIntent.putExtra("owner", owner)
            resultIntent.putExtra("position", position)

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}



