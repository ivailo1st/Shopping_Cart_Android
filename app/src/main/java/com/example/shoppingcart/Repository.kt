package com.example.shoppingcart

object Repository {

    fun addProduct(title:String,quantity:String){
        var newItem = Item(title,quantity)
        MainActivity.itemValues.items.add(newItem)
    }

    fun removeProduct (index: Int){
        MainActivity.itemValues.items.removeAt(index)
    }

    fun deleteAllProducts(){
        MainActivity.itemValues.items.clear()
    }
}