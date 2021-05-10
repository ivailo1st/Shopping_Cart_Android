package com.example.shoppingcart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.itemchange.view.*

class editItem() : DialogFragment(){

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.itemchange, container)
        view.confirmChangeButton.setOnClickListener {
            dismiss()

            var newName = dialog?.findViewById<EditText>(R.id.editTextChangeItemName)
            var newQuantity = dialog?.findViewById<EditText>(R.id.editTextChangeNumber)

            Repository.updateProduct(newName?.text.toString(), newQuantity?.text.toString().toInt())
            RecyclerAdapter().notifyDataSetChanged()


        }
        view.cancelChangeButton.setOnClickListener {
            dismiss()
        }

        return view
    }
}