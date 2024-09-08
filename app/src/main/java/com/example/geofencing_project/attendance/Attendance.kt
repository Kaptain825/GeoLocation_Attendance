package com.example.geofencing_project.attendance

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "attendance")
data class Attendance(
    @PrimaryKey(autoGenerate = true)
    val attendance_id: Int = 0,
    val user_id: Int,
    val location_id: Int,
    val check_in_time: Date,
    val check_out_time: Date?,
    val status: String
)
