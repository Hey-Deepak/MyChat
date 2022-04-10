package com.dc.mychat.domain.repository

import com.dc.mychat.R
import com.dc.mychat.domain.model.Profile

class ProfileRepository {

    fun getAllProfiles(): List<Profile> {
        return listOf(
            Profile(
                name = "Deepak Choudhary", mailId = "choudharydeepak990@gmail.com"    ),
            Profile(
                name = "Deepak2", mailId = "gljat999@gmail.com"),
            Profile(
                name = "Suresh Sharma", mailId = "sureshsharma@gmail.com"),
            Profile(
                name = "Ramesh Yadav", mailId = "crazyremeshyadav@gmail.com"),
            Profile(
                name = "Deepak Choudhary", mailId = "choudharydeepak990@gmail.com"),
            Profile(
                name = "Deepak Choudhary", mailId = "choudharydeepak990@gmail.com"),
            Profile(
                name = "Deepak Choudhary", mailId = "choudharydeepak990@gmail.com"),
            Profile(
                name = "Deepak Choudhary", mailId = "choudharydeepak990@gmail.com")
            )
    }

    fun getProfile(): Profile {
        return Profile(name = "DC", "gmail.com")
    }
}