package com.example.homecookshop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter2(val items:ArrayList<MyData2>) : RecyclerView.Adapter<MyAdapter2.ViewHolder>(){
    interface OnItemClickListener{
        fun OnItemClick(holder: ViewHolder, view: View, data:MyData2, position: Int)
    }

    var itemClickListener:OnItemClickListener?=null

    fun moveItem(oldPos:Int, newPos:Int){
        val item = items[oldPos]
        items.removeAt(oldPos)
        items.add(newPos, item)
        notifyItemMoved(oldPos, newPos)
    }

    fun removeItem(pos:Int){
        items.removeAt(pos)
        notifyItemRemoved(pos)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView1:TextView = itemView.findViewById(R.id.textView1)
        val textView2:TextView = itemView.findViewById(R.id.textView2)
        val textView3:TextView = itemView.findViewById(R.id.textView3)

        init{
            itemView.setOnClickListener {
                itemClickListener?.OnItemClick(this, it, items[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView1.text = items[position].word
        holder.textView2.text = items[position].meaning
        holder.textView3.text = items[position].food

    }

    override fun getItemCount(): Int {
        return items.size
    }
}