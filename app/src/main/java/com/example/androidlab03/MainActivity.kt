package com.example.androidlab03

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //任务1
        findViewById<Button>(R.id.btn_listview).setOnClickListener {
            startActivity(Intent(this, Ex3ListViewActivity::class.java))
        }

        //任务2 自定义弹窗
        findViewById<Button>(R.id.btn_alert_dialog).setOnClickListener {
            val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_login, null)
            val builder = AlertDialog.Builder(this)
            builder.setView(dialogView)
            val dialog = builder.create()
            dialog.show()
            dialogView.findViewById<Button>(R.id.btn_cancel).setOnClickListener { dialog.dismiss() }
            dialogView.findViewById<Button>(R.id.btn_signin).setOnClickListener {
                val user = dialogView.findViewById<EditText>(R.id.et_username).text.toString()
                Toast.makeText(this, "登录用户名：$user", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        }

        //任务3
        findViewById<Button>(R.id.btn_menu).setOnClickListener {
            startActivity(Intent(this, Ex3MenuActivity::class.java))
        }

        //任务4
        findViewById<Button>(R.id.btn_actionmode).setOnClickListener {
            startActivity(Intent(this, Ex3ActionModeActivity::class.java))
        }

        //任务5
        findViewById<Button>(R.id.btn_recycler).setOnClickListener {
            startActivity(Intent(this, Ex3RecyclerActivity::class.java))
        }
    }
}
