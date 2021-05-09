package com.example.shoppingcart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar
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
            RecyclerAdapter().notifyDataSetChanged()


        }
        view.cancelButton.setOnClickListener {
            dismiss()
        }

        return view
    }
}
