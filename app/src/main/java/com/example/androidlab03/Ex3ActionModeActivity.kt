package com.example.androidlab03

import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class Ex3ActionModeActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private var dataList = arrayListOf("One", "Two", "Three", "Four", "Five")
    private var actionMode: ActionMode? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex3_actionmode)
        listView = findViewById(R.id.lv_cab)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, dataList)
        listView.adapter = adapter
        listView.choiceMode = ListView.CHOICE_MODE_SINGLE

        listView.setOnItemLongClickListener { _, view, _, _ ->
            if (actionMode == null) {
                actionMode = startActionMode(actionCallback)
                view.isSelected = true
            }
            true
        }
    }

    private val actionCallback = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            val inflater: MenuInflater = mode!!.menuInflater
            inflater.inflate(R.menu.action_mode_menu, menu)
            mode.title = "1 selected"
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean = false

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            if (item?.itemId == R.id.action_delete) {
                val pos = listView.checkedItemPosition
                if (pos != ListView.INVALID_POSITION) {
                    dataList.removeAt(pos)
                    adapter.notifyDataSetChanged()
                }
                mode?.finish()
            }
            return true
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            actionMode = null
            listView.clearChoices()
        }
    }
}
