package com.dc.mychat.repository

import androidx.compose.ui.res.painterResource
import com.dc.mychat.R
import com.dc.mychat.model.Profile

class ProfileRepository {

    fun getAllProfiles(): List<Profile> {
        return listOf(
            Profile(
                name = "Deepak Choudhary", mailId = "choudharydeepak990@gmail.com", R.drawable.ic_add_profile_picture
                ),
            Profile(
                name = "Deepak2", mailId = "gljat999@gmail.com", R.drawable.ic_add_profile_picture
            ),
            Profile(
                name = "Suresh Sharma", mailId = "sureshsharma@gmail.com", R.drawable.ic_add_profile_picture
            ),
            Profile(
                name = "Ramesh Yadav", mailId = "crazyremeshyadav@gmail.com", R.drawable.ic_add_profile_picture
            ),
            Profile(
                name = "Deepak Choudhary", mailId = "choudharydeepak990@gmail.com", R.drawable.ic_add_profile_picture
            ),
            Profile(
                name = "Deepak Choudhary", mailId = "choudharydeepak990@gmail.com", R.drawable.ic_add_profile_picture
            ),
            Profile(
                name = "Deepak Choudhary", mailId = "choudharydeepak990@gmail.com", R.drawable.ic_add_profile_picture
            ),
            Profile(
                name = "Deepak Choudhary", mailId = "choudharydeepak990@gmail.com", R.drawable.ic_add_profile_picture
            )
            )
    }
}