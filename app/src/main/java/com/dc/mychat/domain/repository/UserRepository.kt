package com.dc.mychat.domain.repository

import android.content.SharedPreferences
import com.dc.mychat.domain.model.Profile
import kotlinx.coroutines.Deferred

interface UserRepository {

    fun getLoginEmailFromPrefs(): String?

    fun saveEmailToPrefs(email: String)

    suspend fun saveProfileToPrefs(profile: Profile)

    suspend fun getProfileFromPrefs(): Profile

    suspend fun saveLoginStatusToPrefs(loginStatus: Boolean)

    suspend fun getLoginStatusFromPrefs(): Boolean

    suspend fun saveProfileStatusToPrefs(status: Boolean)

    suspend fun getProfileStatusToPrefs(): Boolean

}


