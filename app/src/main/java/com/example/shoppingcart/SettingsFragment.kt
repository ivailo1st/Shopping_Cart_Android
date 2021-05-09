package com.example.shoppingcart

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

class SettingsFragment: PreferenceFragmentCompat () {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        val signaturePreference: EditTextPreference? = findPreference("greetingName")

        if (signaturePreference != null) {
            Repository.name = signaturePreference.text

        }
    }
}