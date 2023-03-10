package com.example.movieapp.model.repository

import com.example.movieapp.data.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRepositoryImpl: FirebaseRepository {
    private val database: DatabaseReference = Firebase.database.reference

    override fun updateUserData(firebaseUser: User, uid: String) {
        database.child("users").child(uid).setValue(firebaseUser)
    }
}