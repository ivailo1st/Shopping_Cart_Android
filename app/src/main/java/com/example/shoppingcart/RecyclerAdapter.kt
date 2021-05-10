package com.example.shoppingcart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar


class RecyclerAdapter(var listener: OnItemClickListener? = null) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    interface OnItemClickListener{
        fun UpdateItem(position:Int)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView
        var itemQuantity: TextView

        val buttonDelete = itemView.findViewById<Button>(R.id.remove_button)

        init {
            itemTitle = itemView.findViewById(R.id.item_title)
            itemQuantity = itemView.findViewById(R.id.item_quantity)

            itemView.setOnClickListener{ v: View ->
                var position: Int = getAdapterPosition()
                Snackbar.make(v,"Click detected on item ${itemTitle.text}", Snackbar.LENGTH_SHORT).setAction("Action", null).show()


                listener?.UpdateItem(position)

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
        viewHolder.itemTitle.text = Repository.items[i].title
        viewHolder.itemQuantity.text = Repository.items[i].quantity.toString()
    }
    override fun getItemCount(): Int {
        return Repository.items.size
    }
}