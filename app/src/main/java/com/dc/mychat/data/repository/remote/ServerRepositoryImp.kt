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

    val firestoreDatabaseRef = Firebase.firestore

    override suspend fun createProfile(profile: Profile) {
            firestoreDatabaseRef.collection("Profiles").document(profile.mailId).set(profile).await()
    }


    override suspend fun getAllProfile(): List<Profile>{

        return firestoreDatabaseRef.collection("Profiles").get().await()
            .toObjects(Profile::class.java).filterNotNull()
    }


    override suspend fun getProfile(name: String): Profile? {

        return firestoreDatabaseRef.collection("Profiles").document(name).get().await()
            .toObject(Profile::class.java)
    }

}