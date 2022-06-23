package com.dc.mychat.domain.repository

import android.net.Uri
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.ui.viewmodel.MainViewModel
import java.net.URI

interface ServerRepository {

    suspend fun createProfile(profile: Profile)

    suspend fun uploadProfilePicture(uri: Uri, profile: Profile): String

    suspend fun getAllProfile(): List<Profile>

    suspend fun getProfile(name: String) : Profile?

}