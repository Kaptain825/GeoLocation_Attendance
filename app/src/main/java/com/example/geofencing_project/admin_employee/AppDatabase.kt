package com.example.geofencing_project.admin_employee

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.geofencing_project.database.locations.Location
import com.example.geofencing_project.database.locations.LocationDao
import com.example.geofencing_project.database.users.User
import com.example.geofencing_project.database.users.UserDao

@Database(entities = [AdminEmployee::class, Location::class, User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun adminEmployeeDao(): AdminEmployeeDao
    abstract fun userDao(): UserDao
    abstract fun locationDao(): LocationDao  // Ensure this line is present

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
