package com.dc.mychat.data.repository.local

import android.content.SharedPreferences
import com.dc.mychat.domain.repository.UserRepository

class UserRepositoryImp(
    val prefs: SharedPreferences
    ) : UserRepository {
    override fun getLoggedInEmailFromPrefs(): String? {
        return prefs.getString("emailId", "choudharydeepak@gmail.com")
    }

    override fun saveEmailToPrefs(email: String) {
        prefs.edit().putString("emailId", email).apply()
    }
}