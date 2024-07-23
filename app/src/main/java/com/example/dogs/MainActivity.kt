package com.example.dogs

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dogs.ui.theme.DogsTheme

class MainActivity : ComponentActivity() {

    private lateinit var btnAddDog: Button
    private lateinit var listViewDogs: ListView
    private lateinit var dogAdapter: DogAdapter
    private lateinit var dogList: ArrayList<Dog>

    private val addDogLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            data?.let {
                val name = it.getStringExtra("name")
                val age = it.getStringExtra("age")
                val type = it.getStringExtra("type")
                val owner = it.getStringExtra("owner")
                val position = it.getIntExtra("position", -1)
                if (name != null && age != null && type != null && owner != null) {
                    if (position == -1) {
                        // Add new dog
                        dogList.add(Dog(name, age, type, owner))
                    } else {
                        // Update existing dog
                        dogList[position] = Dog(name, age, type, owner)
                    }
                    dogAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpUI()
    }

    fun setUpUI() {
        setContentView(R.layout.activity_main)


        btnAddDog = findViewById(R.id.btnAddDog)
        listViewDogs = findViewById(R.id.listViewDogs)

        dogList = ArrayList()
        dogAdapter = DogAdapter(this, dogList)
        listViewDogs.adapter = dogAdapter

        btnAddDog.setOnClickListener {
            val intent = Intent(this, AddDogActivity::class.java)
            addDogLauncher.launch(intent)
        }

        listViewDogs.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val dog = dogList[position]
            val intent = Intent(this, AddDogActivity::class.java).apply {
                putExtra("name", dog.name)
                putExtra("age", dog.age)
                putExtra("major", dog.type)
                putExtra("department", dog.owner)
                putExtra("position", position)
            }
            addDogLauncher.launch(intent)
        }
    }
}