package com.example.geofencing_project.admin_employee

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AdminEmployee")
data class AdminEmployee(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val admin_id: Int,
    val employee_id: Int,
    val location_id: Int
)
