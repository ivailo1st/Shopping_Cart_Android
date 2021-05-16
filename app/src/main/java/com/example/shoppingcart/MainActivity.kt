package com.example.shoppingcart

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.ktx.toObject
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), RecyclerAdapter.OnItemClickListener {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val dbCollection = Repository.db.collection("items")

        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(applicationContext)

        val addButton = findViewById<Button>(R.id.ADD_Button)
        val deleteAllButton = findViewById<Button>(R.id.Delete_All_button)
        val shareButton = findViewById<Button>(R.id.Share_List_button)

        val sortItemName = findViewById<Button>(R.id.Name_Sort)
        val sortItemQuantity = findViewById<Button>(R.id.Quantity_Sort)

        val inputTitle = findViewById<EditText>(R.id.Name_Input)
        val inputQuantity = findViewById<EditText>(R.id.Quantity_Input)

        layoutManager = LinearLayoutManager(this)
        item_view.layoutManager = layoutManager

        adapter = RecyclerAdapter(this)
        item_view.adapter = adapter

        dbCollection.addSnapshotListener { value, e ->
            if (e != null) {
                Log.d("Event Listener DB", "Listen failed.", e)
                return@addSnapshotListener
            }
            Repository.items.clear()
            for (doc in value!!) {
                val item = doc.toObject<Item>()
                item.id = doc.id
                Repository.items.add(item)
            }
            (adapter as RecyclerAdapter).notifyDataSetChanged()
        }

        addButton.setOnClickListener{
            Repository.addProduct(inputTitle.text.toString(), if(inputQuantity.text.toString() != ""){inputQuantity.text.toString().toInt()} else{0})
            (adapter as RecyclerAdapter).notifyDataSetChanged()
        }

        deleteAllButton.setOnClickListener{
            val dialog = ConformationDialog()
            dialog.show(supportFragmentManager, "dialog")
        }

        shareButton.setOnClickListener {
            val data = Repository.sendInfo()

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, " Shopping cart: \n$data")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }

        sortItemName.setOnClickListener {
            Repository.items.sortBy { it.title }
            (adapter as RecyclerAdapter).notifyDataSetChanged()
        }

        sortItemQuantity.setOnClickListener {
            Repository.items.sortByDescending { it.quantity }
            (adapter as RecyclerAdapter).notifyDataSetChanged()
        }

        Repository.greetUser(applicationContext)
    }
    override fun UpdateItem(position:Int){
        Repository.oldItemPosition = position
        val dialog = editItem()
        dialog.show(supportFragmentManager, "itemchange")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if(item.itemId == R.id.action_settings){
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            true
        } else{
            super.onOptionsItemSelected(item)
        }
    }
}