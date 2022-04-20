package com.dc.mychat.data.repository.remote

import android.util.Log
import android.widget.Toast
import com.dc.mychat.domain.repository.ServerRepository
import com.dc.mychat.ui.viewmodel.MainViewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class ServerRepositoryImp(): ServerRepository {


    override fun createProfile(mainViewModel: MainViewModel) {
        val storageRef = Firebase.storage.reference.child("Profiles")
        val databaseRef = FirebaseDatabase.getInstance().getReference("Profiles")
        mainViewModel.imageUriState.value?.let {
            storageRef.putFile(it).addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener {
                    databaseRef.setValue(it)
                }
            }
        }

        mainViewModel.profileState.value?.let {
            databaseRef.setValue(it).addOnSuccessListener {
                Log.d("TAG 12", "$it")
            }.addOnFailureListener {
                Log.d("TAG 12", "$it")
            }
        }
    }

}