package com.example.geofencing_project.database.users

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    // LiveData to observe all users
    val allUsers: LiveData<List<User>> = userDao.getAllUsers()

    // Function to insert a new user into the database
    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    // Function to update an existing user in the database
    suspend fun update(user: User) {
        userDao.update(user)
    }

    // Function to get a user by their ID
    suspend fun getUserById(id: Int): User? {
        return userDao.getUserById(id)
    }

    // Function to delete a user by their ID
    suspend fun deleteUserById(id: Int) {
        userDao.deleteUserById(id)
    }

    // Function to get a user by their username
    suspend fun getUserByUsername(username: String): User? {
        return userDao.getUserByUsername(username)
    }
}
