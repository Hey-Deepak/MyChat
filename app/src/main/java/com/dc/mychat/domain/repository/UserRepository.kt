package com.dc.mychat.domain.repository

import android.content.SharedPreferences
import com.dc.mychat.domain.model.Profile

interface UserRepository {

    fun getLoggedInEmailFromPrefs(): String?

    fun saveEmailToPrefs(email: String)

    fun saveProfileToPrefs(profile: Profile)

}


