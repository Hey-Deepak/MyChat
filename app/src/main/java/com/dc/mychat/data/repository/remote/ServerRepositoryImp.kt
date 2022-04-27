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
    val storageRef = Firebase.storage.reference.child("Profiles")
    val databaseRef = Firebase.database.reference.child("Profiles")
    val firestoreDatabaseRef = Firebase.firestore
    var listOfProfile = mutableListOf<Profile>()

    override suspend fun createProfile(mainViewModel: MainViewModel) {

        mainViewModel.profileState.value?.let {

            Log.d("TAG 12 ServerRepository", "$it")
            firestoreDatabaseRef.collection("Profiles").add(it).addOnSuccessListener {
                Log.d("TAG 17 ServerRepository inside firebaseDatabaseRef", "$it")
            }
        }
    }

    override suspend fun getAllProfile(mainViewModel: MainViewModel) {

        firestoreDatabaseRef.collection("Profiles").get().addOnSuccessListener {
            val listOfProffiless = mutableListOf<Profile>()

            for (profile in it.documents){
               val data = profile.data
                val displayName = data?.get("displayName")
                val displayPhoto = data?.get("displayPhoto")
                val emailId = data?.get("mailId")
                listOfProffiless.add(Profile(displayName.toString(), emailId.toString(), displayPhoto.toString()))
            }
               Log.d("TAG 21" ,"${listOfProffiless} ")


            mainViewModel.allUsersState.value = listOfProffiless
            Log.d("TAG 18", " List of profiles ${listOfProffiless}")
        }
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