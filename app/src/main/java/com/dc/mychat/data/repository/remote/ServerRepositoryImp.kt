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
    val databaseRef = Firebase.database.reference.child("Profiless")
    val listOfProfile = mutableListOf<Profile>()

    override fun createProfile(mainViewModel: MainViewModel) {

        /*mainViewModel.imageUriState.value?.let {
            storageRef.putFile(it).addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener {
                    databaseRef.setValue(it)
                }
            }
        }*/

        mainViewModel.profileState.value?.let {
            databaseRef.setValue(it).addOnSuccessListener {
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

}