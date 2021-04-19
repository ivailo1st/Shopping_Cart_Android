package com.example.shoppingcart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    object itemValues{
        var items = mutableListOf(Item("Pepper","1"), Item("Peach","3"), Item("Stick","2"), Item("Rubber Ducky","5"))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addButton = findViewById<Button>(R.id.ADD_Button)
        val deleteAllButton = findViewById<Button>(R.id.Delete_All_button)

        var inputTitle = findViewById<EditText>(R.id.Name_Input)
        var inputQuantity = findViewById<EditText>(R.id.Quantity_Input)

        layoutManager = LinearLayoutManager(this)
        item_view.layoutManager = layoutManager

        adapter = RecyclerAdapter()
        item_view.adapter = adapter

        addButton.setOnClickListener{v: View->
            Repository.addProduct(inputTitle.text.toString(),inputQuantity.text.toString())

            (adapter as RecyclerAdapter).notifyDataSetChanged()

        }

        deleteAllButton.setOnClickListener{v: View->
            Repository.deleteAllProducts()

            (adapter as RecyclerAdapter).notifyDataSetChanged()
        }
    }
}