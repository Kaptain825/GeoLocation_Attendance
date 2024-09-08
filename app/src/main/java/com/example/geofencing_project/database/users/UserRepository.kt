package com.example.yourapp.data

import androidx.lifecycle.LiveData
import com.example.geofencing_project.database.users.User
import com.example.geofencing_project.database.users.UserDao

class UserRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun update(user: User) {
        userDao.update(user)
    }

    suspend fun getUserById(id: Int): User? {
        return userDao.getUserById(id)
    }

    suspend fun deleteUserById(id: Int) {
        userDao.deleteUserById(id)
    }
}
