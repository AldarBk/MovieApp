package com.example.movieapp.model.repository

import com.example.movieapp.data.User

interface FirebaseRepository {
    fun updateUserData(firebaseUser: User, uid: String)
}