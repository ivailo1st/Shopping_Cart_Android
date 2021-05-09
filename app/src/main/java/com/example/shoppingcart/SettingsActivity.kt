package com.example.shoppingcart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        if(findViewById<View>(R.id.fragment_container) != null){
            if(savedInstanceState != null){
                return
            }
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, SettingsFragment()).commit()
        }
    }
}