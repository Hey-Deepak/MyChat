package com.dc.mychat.domain.repository

import android.content.SharedPreferences
import com.dc.mychat.domain.model.Profile

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
