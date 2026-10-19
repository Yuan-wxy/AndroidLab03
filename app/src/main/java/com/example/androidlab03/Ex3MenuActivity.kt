package com.example.androidlab03

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Ex3MenuActivity : AppCompatActivity() {
    private lateinit var tvTest: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex3_menu)
        tvTest = findViewById(R.id.tv_test_text)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_small -> tvTest.textSize = 10f
            R.id.menu_mid -> tvTest.textSize = 16f
            R.id.menu_big -> tvTest.textSize = 20f
            R.id.menu_normal -> Toast.makeText(this, "点击了普通菜单项", Toast.LENGTH_SHORT).show()
            R.id.menu_red -> tvTest.setTextColor(Color.RED)
            R.id.menu_black -> tvTest.setTextColor(Color.BLACK)
        }
        return true
    }
}
