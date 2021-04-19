package com.example.shoppingcart

import android.view.LayoutInflater
import android.widget.TextView
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private val titles = arrayOf("Pepper","Peach","Stick","Rubber Ducky","Another Sample","Do we break")
    private val quantity = arrayOf(1,3,2,5,6,1)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView
        var itemQuantity: TextView

        init {
            itemTitle = itemView.findViewById(R.id.item_title)
            itemQuantity = itemView.findViewById(R.id.item_quantity)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemQuantity.text = quantity[i].toString()
    }
    override fun getItemCount(): Int {
        return titles.size
    }
}