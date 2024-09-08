package com.example.geofencing_project.database.users

class UserRepository(private val userDao: UserDao) {

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun getUserById(userId: Int): User? {
        return userDao.getUserById(userId)
    }

    suspend fun getUserByUsername(username: String): User? {
        return userDao.getUserByUsername(username)
    }

    suspend fun getAllAdmins(): List<User> {
        return userDao.getAllAdmins()
    }

    suspend fun getAllEmployees(): List<User> {
        return userDao.getAllEmployees()
    }
}
