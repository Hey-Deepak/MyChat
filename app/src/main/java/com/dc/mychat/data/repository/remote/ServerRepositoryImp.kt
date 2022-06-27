package com.dc.mychat.data.repository.remote

import android.net.Uri
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.domain.repository.ServerRepository
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await

class ServerRepositoryImp(): ServerRepository {

    private val firestoreDatabaseRef = Firebase.firestore
    private val storageRef = Firebase.storage.reference

    override suspend fun createProfile(profile: Profile) {
            firestoreDatabaseRef.collection("Profiles").document(profile.mailId).set(profile).await()
    }

    override suspend fun uploadProfilePicture(uri: Uri, profile: Profile): String {
        storageRef.child("images/${profile.mailId}").putFile(uri).await()
        val photoPath = storageRef.child("images/${profile.mailId}").downloadUrl.await()
        return photoPath.toString()
    }


    override suspend fun getAllProfile(): List<Profile>{

        return firestoreDatabaseRef.collection("Profiles").get().await()
            .toObjects(Profile::class.java).filterNotNull()
    }


    override suspend fun getProfile(name: String): Profile? {

        return firestoreDatabaseRef.collection("Profiles").document(name).get().await()
            .toObject(Profile::class.java)
    }

    override suspend fun fetchProfile(it: FirebaseUser): Profile? {
        return firestoreDatabaseRef.collection("Profiles").document(it.email.toString()).get().await()
            .toObject(Profile::class.java)
    }

}