package com.example.shoppingcart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog.view.*


class ConformationDialog() : DialogFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.dialog, container)
        view.confirmButton.setOnClickListener {
            dismiss()
            Repository.deleteAllProducts()
        }
        view.cancelButton.setOnClickListener {
            dismiss()
        }
        return view
    }
}
