package com.dc.mychat.data.repository.remote

import android.nfc.Tag
import android.util.Log
import android.util.Log.d
import android.widget.Toast
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.domain.repository.ServerRepository
import com.dc.mychat.ui.viewmodel.MainViewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await

class ServerRepositoryImp(): ServerRepository {

    val databaseRef = Firebase.database.reference.child("Profiles")
    val firestoreDatabaseRef = Firebase.firestore
    var listOfProfile = mutableListOf<Profile>()

    override suspend fun createProfile(mainViewModel: MainViewModel) {

        mainViewModel.profileState.value?.let {
            firestoreDatabaseRef.collection("Profiles").document(it.mailId).set(it).addOnSuccessListener {
            }
        }
    }


    override suspend fun getAllProfile(mainViewModel: MainViewModel) {

        firestoreDatabaseRef.collection("Profiles").get().addOnSuccessListener {

            for (profile in it.documents){
               val data = profile.data
                val displayName = data?.get("displayName")
                val displayPhoto = data?.get("displayPhoto")
                val emailId = data?.get("mailId")
                listOfProfile.add(Profile(displayName.toString(), emailId.toString(), displayPhoto.toString()))
            }

            mainViewModel.allUsersState.value = listOfProfile
            Log.d("TAG 18", " List of profiles ${listOfProfile}")
        }
    }


    override fun getProfile(name: String): Profile {

        val profile = Profile(name," "," ")
        databaseRef.child(name).get().addOnSuccessListener {
            profile.displayName = it.child("displayName").value.toString()
            profile.displayPhoto = it.child("displayPhoto").value.toString()
            profile.mailId = it.child("mailId").value.toString()
        }

        return profile

    }

}