package com.dc.mychat.domain.repository

import com.dc.mychat.domain.model.Profile
import com.google.firebase.auth.FirebaseUser

interface ServerRepository {

    suspend fun createProfile(profile: Profile)

    suspend fun uploadProfilePicture(profile: Profile): String

    suspend fun getAllProfile(): List<Profile>

    suspend fun getProfile(name: String) : Profile?

    suspend fun fetchProfile(it: FirebaseUser): Profile?

}