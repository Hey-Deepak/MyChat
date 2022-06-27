package com.dc.mychat.data.repository.local

import android.content.SharedPreferences
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.domain.repository.UserRepository
import com.google.gson.Gson

class UserRepositoryImp(
    val prefs: SharedPreferences,
) : UserRepository {
    override fun getLoginEmailFromPrefs(): String? {
        return prefs.getString("emailId", null)
    }

    override fun saveEmailToPrefs(email: String) {
        prefs.edit().putString("emailId", email).apply()
    }

    override suspend fun saveProfileToPrefs(profile: Profile) {
        prefs.edit().putString("profile", Gson().toJson(profile)).apply()
    }

    override suspend fun getProfileFromPrefs(): Profile {
        val profileJson = prefs.getString("profile", null)
        return Gson().fromJson(profileJson, Profile::class.java)
    }

    override suspend fun saveLoginStatusToPrefs(loginStatus: Boolean) {
        prefs.edit().putBoolean("loginStatus", loginStatus).apply()
    }

    override suspend fun getLoginStatusFromPrefs(): Boolean {
        return prefs.getBoolean("loginStatus", false)
    }

    override suspend fun saveProfileStatusToPrefs(status: Boolean) {
        prefs.edit().putBoolean("profileStatus", status).apply()
    }

    override suspend fun getProfileStatusToPrefs(): Boolean {
        return prefs.getBoolean("profileStatus", false)
    }
}