package com.dc.mychat.data.repository.local

import android.content.SharedPreferences
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.domain.repository.UserRepository

class UserRepositoryImp(
    val prefs: SharedPreferences,
) : UserRepository {
    override fun getLoggedInEmailFromPrefs(): String? {
        return prefs.getString("emailId", "choudharydeepak@gmail.com")
    }

    override fun saveEmailToPrefs(email: String) {
        prefs.edit().putString("emailId", email).apply()
    }

   /* override fun saveProfileToPrefs(profile: Profile) {
        prefs.edit().putString("emailId", profile.mailId).apply()
        prefs.edit().putString("displayName", profile.displayName).apply()
        prefs.edit().putString("displayPhoto", profile.displayPhoto).apply()
    }*/

    override fun saveProfileToPrefs(profile: Profile) {
        prefs.edit().putString("profile", profile.toString()).apply()
        prefs.edit().putString("emailId", profile.mailId).apply()
        prefs.edit().putString("displayName", profile.displayName).apply()
        prefs.edit().putString("displayPhoto", profile.displayPhoto).apply()
    }
}