package com.example.shoppingcart

import android.content.Context
import android.util.Log
import android.widget.Toast

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

object Repository {

    var name = "user"
    val db = Firebase.firestore
    var oldItemPosition = 0
    var items = mutableListOf<Item>()

    fun greetUser(context:Context){
        Toast.makeText(context,"Hello and Welcome $name ", Toast.LENGTH_SHORT).show()
    }

    fun sendInfo(): String {
        var result = ""
        for(item in items){
            result += "Title: " + item.title + " Quantity: " + item.quantity + "\n"
        }
        return result
    }

    fun addEventListener(){
        val dbCollection = db.collection("items")
        dbCollection.addSnapshotListener { value, e ->
                if (e != null) {
                    Log.d("Event Listener DB", "Listen failed.", e)
                    return@addSnapshotListener
                }
                items.clear()
                for (doc in value!!) {
                    val item = doc.toObject<Item>()
                    item.id = doc.id
                    items.add(item)
                }
                Log.d("Items","Items: ${items}")
            }
        RecyclerAdapter().notifyDataSetChanged()
    }

    fun addProduct(title:String,quantity:Int){
        var newItem = Item(title,quantity)
        db.collection("items")
            .add(newItem)
            .addOnSuccessListener { documentReference ->
                Log.d("Error", "DocumentSnapshot written with ID: " + documentReference.id)
                newItem.id = documentReference.id
            }
            .addOnFailureListener{ e -> Log.w("Error","Error adding document", e)}
        items.add(newItem)
    }

    fun updateProduct(newName: String, newQuantity:Int){
        val item = items[oldItemPosition]
        db.collection("items").document(item.id).update("title",newName,"quantity",newQuantity).addOnSuccessListener {
            Log.d("Snapshot","DocumentSnapshot with id: ${item.id} successfully updated!")

        }
        item.title = newName
        item.quantity = newQuantity
    }

    fun removeProduct (index: Int){
        val item = items[index]
        db.collection("items").document(item.id).delete().addOnSuccessListener {
            Log.d("Snapshot","DocumentSnapshot with id: ${item.id} successfully deleted!")

        }
        items.removeAt(index)
    }

    fun deleteAllProducts(){
        val dbBatch = db.batch()
        for(item in items){
            val specificItem = db.collection("items").document(item.id)
            dbBatch.delete(specificItem)
        }
        dbBatch.commit().addOnCompleteListener {
            RecyclerAdapter().notifyDataSetChanged()
        }
        items.clear()
    }
}