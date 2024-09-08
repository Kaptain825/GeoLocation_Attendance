package com.example.geofencing_project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.geofencing_project.database.users.UserRepository
import com.example.geofencing_project.database.users.UserViewModel

class UserViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

