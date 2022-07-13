package com.dc.mychat.domain.repository


import com.dc.mychat.domain.model.Profile


interface LocalRepository {


    // Email
    fun saveEmailToPrefs(email: String)
    fun getLoginEmailFromPrefs(): String?

    //Profile
    suspend fun saveProfileToPrefs(profile: Profile)
    suspend fun getProfileFromPrefs(): Profile

    // Status (Is?)
    suspend fun saveLoginStatusToPrefs(loginStatus: Boolean)
    suspend fun saveIsProfileCreatedStatusToPrefs(status: Boolean)

    suspend fun getLoginStatusFromPrefs(): Boolean
    suspend fun getIsProfileCreatedFromPrefs(): Boolean

}


