package com.example.geofencing_project.database.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    // Expose all users as LiveData
    val allUsers: LiveData<List<User>> = userRepository.allUsers

    // Method to insert a user
    fun insert(user: User) = viewModelScope.launch {
        userRepository.insert(user)
    }

    // Method to get a user by ID
    suspend fun getUserById(id: Int): User? {
        return userRepository.getUserById(id)
    }

    // Method to delete a user
    fun deleteUser(id: Int) = viewModelScope.launch {
        userRepository.deleteUserById(id)
    }

    // Method to get a user by username
    suspend fun getUserByUsername(username: String): User? {
        return userRepository.getUserByUsername(username)
    }
}
