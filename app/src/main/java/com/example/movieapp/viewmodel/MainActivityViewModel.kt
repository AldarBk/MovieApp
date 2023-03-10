package com.example.movieapp.viewmodel

import com.example.movieapp.model.repository.FirebaseRepository
import com.example.movieapp.model.repository.FirebaseRepositoryImpl
import com.example.movieapp.data.User

class MainActivityViewModel {
    private val mFirebaseRepository: FirebaseRepository = FirebaseRepositoryImpl()

    fun updateUserData(firebaseUser: User, uid: String){
        mFirebaseRepository.updateUserData(firebaseUser,uid)
    }
}