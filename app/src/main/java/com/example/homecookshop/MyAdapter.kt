package com.example.homecookshop

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.marginRight
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
    fun shopping(itemView: ViewHolder, data: MyData, pos:Int){
        if(itemView.textView.paintFlags == Paint.STRIKE_THRU_TEXT_FLAG){
            data.isOpen = true
            itemView.textView.paintFlags = 0
            itemView.textView.setTextColor(Color.BLACK)
            itemView.imgView.visibility = View.GONE
            Toast.makeText(itemView.textView.context,data.word+"\n \n 장바구니해제",Toast.LENGTH_SHORT).show()
        }else if(itemView.textView.paintFlags == 0){
            data.isOpen = false
            itemView.textView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            itemView.textView.setTextColor(Color.GRAY)
            itemView.imgView.visibility = View.VISIBLE
            Toast.makeText(itemView.textView.context,data.word+"\n \n 장바구니추가",Toast.LENGTH_SHORT).show()
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView:TextView = itemView.findViewById(R.id.textView)
        val imgView: ImageView = itemView.findViewById(R.id.check_img)
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
        //holder.textView2.text = items[position].meaning
        if(items[position].isOpen){
            holder.textView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            holder.textView.setTextColor(Color.GRAY)
            holder.imgView.visibility = View.VISIBLE
        }
        else{
            holder.textView.paintFlags = 0
            holder.textView.setTextColor(Color.BLACK)
            holder.imgView.visibility = View.GONE
        }
        holder.textView.setOnClickListener {
        //val isOpen = false
        //val pos = position
        //Toast.makeText(holder.textView.context, "clicked", Toast.LENGTH_SHORT).show()
        //click event
            shopping(holder,items[position],position)
        }
        holder.textView.setOnLongClickListener {
            val intent = Intent(holder.textView?.context, AddVocActivity::class.java)
           // val isOpen = false
            val pos = position
            //intent.putExtra("isOpen",isOpen)
            intent.putExtra("pos", pos)
            ContextCompat.startActivity(holder.textView.context, intent, null)
            true
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}