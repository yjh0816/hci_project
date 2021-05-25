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

    fun showMeaning(itemView: ViewHolder, data: MyData2, pos:Int){
        if(itemView.textView2.visibility == View.GONE){
            data.isOpen = true
            itemView.textView2.visibility = View.VISIBLE

        }else if(itemView.textView2.visibility == View.VISIBLE){
            data.isOpen = false
            itemView.textView2.visibility = View.GONE

        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView:TextView = itemView.findViewById(R.id.textView)
        val textView2:TextView = itemView.findViewById(R.id.textView2)
        init{
            itemView.setOnClickListener {
                itemClickListener?.OnItemClick(this, it, items[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position].word
        holder.textView2.text = items[position].meaning+" "+items[position].food

        if(items[position].isOpen)
            holder.textView2.visibility = View.VISIBLE
        else
            holder.textView2.visibility = View.GONE
    }

    override fun getItemCount(): Int {
        return items.size
    }
}