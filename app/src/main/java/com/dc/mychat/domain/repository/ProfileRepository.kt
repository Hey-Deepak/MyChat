package com.dc.mychat.domain.repository

import com.dc.mychat.R
import com.dc.mychat.domain.model.Profile

interface ProfileRepository {

   // fun getAllProfiles(): List<Profile>

    fun getProfile(): Profile

}