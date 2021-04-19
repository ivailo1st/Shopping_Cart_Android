package com.example.shoppingcart

import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView
        var itemQuantity: TextView

        val buttonDelete = itemView.findViewById<Button>(R.id.remove_button)

        init {
            itemTitle = itemView.findViewById(R.id.item_title)
            itemQuantity = itemView.findViewById(R.id.item_quantity)

            itemView.setOnClickListener{ v: View ->
                var position: Int = getAdapterPosition()

                Snackbar.make(v,"Click detected on item $position", Snackbar.LENGTH_SHORT).setAction("Action", null).show()
            }

            buttonDelete.setOnClickListener{v: View ->
                val position: Int = adapterPosition

                Repository.removeProduct(position)

                notifyItemRemoved(position)

            }

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = MainActivity.itemValues.items[i].Title
        viewHolder.itemQuantity.text = MainActivity.itemValues.items[i].Quantity
    }
    override fun getItemCount(): Int {
        return MainActivity.itemValues.items.size
    }
}