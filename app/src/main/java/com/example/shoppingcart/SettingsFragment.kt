package com.example.shoppingcart

import android.os.Bundle
import android.util.Log
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat



class SettingsFragment: PreferenceFragmentCompat () {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        val newGreetingName: EditTextPreference? = findPreference("greetingName")
        newGreetingName!!.onPreferenceChangeListener = Preference.OnPreferenceChangeListener {
            preference, newValue ->
                Repository.name = newValue.toString()
                false
        }
    }
}