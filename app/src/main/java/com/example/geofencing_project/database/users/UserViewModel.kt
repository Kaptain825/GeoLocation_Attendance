package com.example.geofencing_project.database.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun getUserByIdAndRole(userId: Int, role: String): LiveData<User> {
        return repository.getUserByIdAndRole(userId, role)
    }


    suspend fun getUserByUsername(username: String): User? {
        return repository.getUserByUsername(username)
    }

    fun getAllAdmins() = viewModelScope.launch {
        repository.getAllAdmins()
    }

    fun getAllEmployees() = viewModelScope.launch {
        repository.getAllEmployees()
    }
}
