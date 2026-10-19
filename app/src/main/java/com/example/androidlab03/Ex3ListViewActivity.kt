package com.example.androidlab03

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import java.util.ArrayList
import java.util.HashMap

class Ex3ListViewActivity : AppCompatActivity() {
    private val CHANNEL_ID = "animal_notify"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex3_listview)
        createNotifyChannel()

        val lvAnimal = findViewById<ListView>(R.id.lv_animal)
        val dataList = ArrayList<HashMap<String, Any>>()
        dataList.add(hashMapOf("name" to "Lion", "pic" to R.drawable.lion))
        dataList.add(hashMapOf("name" to "Tiger", "pic" to R.drawable.tiger))
        dataList.add(hashMapOf("name" to "Monkey", "pic" to R.drawable.monkey))
        dataList.add(hashMapOf("name" to "Dog", "pic" to R.drawable.dog))
        dataList.add(hashMapOf("name" to "Cat", "pic" to R.drawable.cat))
        dataList.add(hashMapOf("name" to "Elephant", "pic" to R.drawable.elephant))

        val adapter = SimpleAdapter(
            this,
            dataList,
            R.layout.item_animal,
            arrayOf("name", "pic"),
            intArrayOf(R.id.tv_name, R.id.iv_animal)
        )
        lvAnimal.adapter = adapter

        lvAnimal.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val map = dataList[position]
                val animalName = map["name"].toString()
                Toast.makeText(this, "选中：$animalName", Toast.LENGTH_SHORT).show()
                sendNotification(animalName)
            }
    }

    private fun createNotifyChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "动物通知",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nm.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(titleText: String) {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(titleText)
            .setContentText("你点击选中了动物条目")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.notify(1001, builder.build())
    }
}
