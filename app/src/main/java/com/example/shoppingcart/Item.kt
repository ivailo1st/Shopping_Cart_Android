package com.example.shoppingcart

import com.google.firebase.firestore.Exclude

data class Item(var title:String = "", var quantity: Int = 0, @get:Exclude var id: String="")