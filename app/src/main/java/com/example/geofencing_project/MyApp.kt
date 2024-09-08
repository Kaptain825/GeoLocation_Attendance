package com.example.geofencing_project

import android.app.Application
import com.example.geofencing_project.admin_employee.AdminEmployeeRepository
import com.example.geofencing_project.admin_employee.AppDatabase
import com.example.geofencing_project.database.users.UserRepository
import com.example.geofencing_project.database.users.UserRoomDatabase
import com.example.geofencing_project.database.locations.LocationRepository
import com.example.geofencing_project.database.locations.LocationRoomDatabase

class MyApp : Application() {
    val userRepository by lazy { UserRepository(UserRoomDatabase.getDatabase(this).userDao()) }
    val adminEmployeeRepository by lazy { AdminEmployeeRepository(AppDatabase.getDatabase(this).adminEmployeeDao()) }
    val locationRepository by lazy { LocationRepository(LocationRoomDatabase.getDatabase(this).locationDao()) }
}
