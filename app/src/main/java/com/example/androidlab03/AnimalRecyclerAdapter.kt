package com.example.androidlab03

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnimalRecyclerAdapter(
    private val list: MutableList<Animal>,
    private val onItemClick: (Animal) -> Unit
) : RecyclerView.Adapter<AnimalRecyclerAdapter.VH>() {

    inner class VH(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val iv: ImageView = itemView.findViewById(R.id.iv_animal)
        val tv: TextView = itemView.findViewById(R.id.tv_name)
        init {
            itemView.setOnClickListener {
                val pos = adapterPosition
                if(pos != RecyclerView.NO_POSITION){
                    onItemClick(list[pos])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_animal, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = list[position]
        holder.tv.text = item.name
        holder.iv.setImageResource(item.imgRes)
    }

    override fun getItemCount(): Int = list.size

    fun addNewItem(animal: Animal){
        list.add(animal)
        notifyItemInserted(list.size - 1)
    }
}
