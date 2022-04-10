package com.dc.mychat.domain.repository

import android.content.SharedPreferences
import com.dc.mychat.domain.model.Profile

interface UserRepository {

    fun getLoggedInEmailFromPrefs(): String?

    fun saveEmailToPrefs(email: String)

}
/*
    fun getLoggedInEmailFromPrefs(): String? {
        return prefs.getString("emailId", "choudharydeepak@gmail.com")
    }

    fun saveEmailToPrefs(email: String) {
        prefs.edit().putString("emailId", email).apply()
    }
    fun saveProfileToPrefs(profile: Profile) {
        prefs.edit().putString("profile", profile.toString()).apply()
    }*/

