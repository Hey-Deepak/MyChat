package com.dc.mychat.repository

import android.content.SharedPreferences
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import com.dc.mychat.model.Profile

class UserRepository(val prefs: SharedPreferences) {


    fun getLoggedInEmailFromPrefs(): String? {
        return prefs.getString("emailId", null)
    }

    fun saveEmailToPrefs(email: String) {
        prefs.edit().putString("emailId", email).apply()
    }
    fun saveProfileToPrefs(profile: Profile) {
        prefs.edit().putString("profile", profile.toString()).apply()
    }
}
