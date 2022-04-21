package com.dc.mychat.data.repository.remote

import android.util.Log
import android.widget.Toast
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.domain.repository.ServerRepository
import com.dc.mychat.ui.viewmodel.MainViewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class ServerRepositoryImp(): ServerRepository {
    val storageRef = Firebase.storage.reference.child("Profiles")
    val databaseRef = Firebase.database.reference.child("Profiles")
    val listOfProfile = mutableListOf<Profile>()

    override fun createProfile(mainViewModel: MainViewModel) {

        mainViewModel.profileState.value?.let {
            databaseRef.child(mainViewModel.profileState.value.displayName).get().addOnSuccessListener {
                mainViewModel.profileState.value.displayName = it.child("displayName").value.toString()
                mainViewModel.profileState.value.displayPhoto = it.child("displayPhoto").value.toString()
                mainViewModel.profileState.value.mailId = it.child("mailId").value.toString()
                Log.d("TAG 14", "${it.value}")
            }

        }

        mainViewModel.profileState.value?.let {
            databaseRef.child(it.displayName).setValue(it).addOnSuccessListener {
                Log.d("TAG 12", "$it")
            }.addOnFailureListener {
                Log.d("TAG 12 database ref failure", "$it")
            }
        }
    }

    override fun getAllProfile() : List<Profile> {
        databaseRef.get().addOnSuccessListener {
            it.value
            Log.d("TAG12", "${it.value}")
        }
        return listOfProfile
    }

    override fun getProfile(name: String): Profile {
        val profile = Profile(name," "," ")
        databaseRef.child(name).get().addOnSuccessListener {
            profile.displayName = it.child("displayName").value.toString()
            profile.displayPhoto = it.child("displayPhoto").value.toString()
            profile.mailId = it.child("mailId").value.toString()
            Log.d("TAG 13", "${it.value}")
            Log.d("TAG 14", "${profile}")
        }

        return profile

    }

}