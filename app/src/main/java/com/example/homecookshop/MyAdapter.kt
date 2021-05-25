package com.example.homecookshop

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val items:ArrayList<MyData>) : RecyclerView.Adapter<MyAdapter.ViewHolder>(){
    interface OnItemClickListener{
        fun OnItemClick(holder: ViewHolder, view: View, data:MyData, position: Int)
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

    fun showMeaning(itemView: ViewHolder, data: MyData, pos:Int){
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
            itemView.setOnLongClickListener {
                true
                //itemClickListener?.OnItemClick(this, it, items[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position].word
        holder.textView2.text = items[position].meaning

        if(items[position].isOpen)
            holder.textView2.visibility = View.VISIBLE
        else
            holder.textView2.visibility = View.GONE
        holder.textView.setOnClickListener {
        val isOpen = false
        val pos = position
        Toast.makeText(holder.textView.context, "clicked", Toast.LENGTH_SHORT).show()
        //click event
        }
        holder.textView.setOnLongClickListener {
            val intent = Intent(holder.textView?.context, AddVocActivity::class.java)
            val isOpen = false
            val pos = position
            intent.putExtra("isOpen",isOpen)
            intent.putExtra("pos", pos)
            ContextCompat.startActivity(holder.textView.context, intent, null)
            true
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}