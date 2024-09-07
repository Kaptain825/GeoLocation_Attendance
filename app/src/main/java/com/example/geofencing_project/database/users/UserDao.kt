package com.example.geofencing_project.database.users

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Query
import androidx.room.Delete

@Dao
interface UserDao {

    // Query to get all users
    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<User>>

    // Query to get a user by username
    @Query("SELECT * FROM users WHERE name = :username")
    suspend fun getUserByUsername(username: String): User?

    // Query to get a user by ID
    @Query("SELECT * FROM users WHERE userid = :id")
    suspend fun getUserById(id: Int): User?

    // Insert a new user
    @Insert
    suspend fun insert(user: User)

    // Update an existing user
    @Update
    suspend fun update(user: User)

    // Delete a user by ID
    @Query("DELETE FROM users WHERE userid = :id")
    suspend fun deleteUserById(id: Int)
}
