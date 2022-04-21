package com.dc.mychat.data.repository.local

import android.content.SharedPreferences
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.domain.repository.ProfileRepository
import com.google.firebase.firestore.FirebaseFirestore

class ProfileRepositoryImp(
    val prefs: SharedPreferences
) : ProfileRepository {
    /*override fun getAllProfiles(): List<Profile> {
        TODO("Not yet implemented")
    }*/

    override fun getProfile(): Profile {
            return Profile(
                prefs.getString("displayName", "defaultValue").toString(),
                prefs.getString("emailId", "defaultValue").toString(),
                prefs.getString("displayPhoto", "defaultValue").toString()
            )
        }
    }