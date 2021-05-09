package com.example.shoppingcart

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Repository.addEventListener()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(applicationContext)


        val addButton = findViewById<Button>(R.id.ADD_Button)
        val deleteAllButton = findViewById<Button>(R.id.Delete_All_button)
        val shareButton = findViewById<Button>(R.id.Share_List_button)

        val sortItemName = findViewById<Button>(R.id.Name_Sort)
        val sortItemQuantity = findViewById<Button>(R.id.Quantity_Sort)

        var inputTitle = findViewById<EditText>(R.id.Name_Input)
        var inputQuantity = findViewById<EditText>(R.id.Quantity_Input)

        layoutManager = LinearLayoutManager(this)
        item_view.layoutManager = layoutManager

        adapter = RecyclerAdapter()
        item_view.adapter = adapter

        addButton.setOnClickListener{v: View->
            Repository.addProduct(inputTitle.text.toString(),inputQuantity.text.toString().toInt())
            (adapter as RecyclerAdapter).notifyDataSetChanged()

        }

        deleteAllButton.setOnClickListener{v: View->
            val dialog = ConformationDialog()
            dialog.show(supportFragmentManager, "dialog")
            Toast.makeText(applicationContext,"Refresh is required to see data change", Toast.LENGTH_LONG).show()
        }

        shareButton.setOnClickListener { v: View->
            var data = Repository.sendInfo()

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, " Shopping cart: \n$data")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }

        sortItemName.setOnClickListener { v: View? ->
            Repository.items.sortBy { it.title }
            (adapter as RecyclerAdapter).notifyDataSetChanged()
        }

        sortItemQuantity.setOnClickListener { v: View? ->
            Repository.items.sortByDescending { it.quantity }
            (adapter as RecyclerAdapter).notifyDataSetChanged()
        }

        Repository.greetUser(applicationContext)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_settings){
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            return true;
        }
        else{
            return super.onOptionsItemSelected(item)
        }
    }
}