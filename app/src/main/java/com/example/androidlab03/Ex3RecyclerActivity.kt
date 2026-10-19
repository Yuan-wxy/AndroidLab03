package com.example.androidlab03

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Ex3RecyclerActivity : AppCompatActivity() {
    private lateinit var adapter: AnimalRecyclerAdapter
    private val animalList = mutableListOf<Animal>()

    private val animalPool = listOf(
        Animal("Lion", R.drawable.lion),
        Animal("Tiger", R.drawable.tiger),
        Animal("Dog", R.drawable.dog),
        Animal("Monkey", R.drawable.monkey),
        Animal("Cat", R.drawable.cat),
        Animal("Elephant", R.drawable.elephant)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex3_recycler)

        animalList.add(Animal("Lion", R.drawable.lion))
        animalList.add(Animal("Tiger", R.drawable.tiger))
        animalList.add(Animal("Dog", R.drawable.dog))

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        adapter = AnimalRecyclerAdapter(animalList) { animal ->
            Toast.makeText(this, "选中：${animal.name}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter

        val btnAdd = findViewById<Button>(R.id.btn_add)
        btnAdd.setOnClickListener {
            val randomAnimal = animalPool.random()
            animalList.add(randomAnimal)
            adapter.notifyItemInserted(animalList.size - 1)
            recyclerView.scrollToPosition(animalList.size - 1)
        }
    }
}
