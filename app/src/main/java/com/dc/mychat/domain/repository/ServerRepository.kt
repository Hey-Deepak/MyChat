package com.dc.mychat.domain.repository

import com.dc.mychat.domain.model.Profile
import com.dc.mychat.ui.viewmodel.MainViewModel

interface ServerRepository {

    suspend fun createProfile(profile: Profile)

    suspend fun getAllProfile(): List<Profile>

    suspend fun getProfile(name: String) : Profile?

}