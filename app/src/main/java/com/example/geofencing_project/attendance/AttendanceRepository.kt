package com.example.geofencing_project.attendance

import androidx.lifecycle.LiveData
import java.util.Date

class AttendanceRepository(private val attendanceDao: AttendanceDao) {

    // LiveData to observe all attendance records
    val allAttendance: LiveData<List<Attendance>> = attendanceDao.getAllAttendance()

    // Insert an attendance record into the database
    suspend fun insertAttendance(attendance: Attendance) {
        attendanceDao.insertAttendance(attendance)
    }

    // Get an attendance record by its ID
    suspend fun getAttendanceById(id: Int): Attendance? {
        return attendanceDao.getAttendanceById(id)
    }

    // Delete an attendance record by its ID
    suspend fun deleteAttendance(id: Int) {
        attendanceDao.deleteAttendance(id)
    }

    // Update an attendance record
    suspend fun updateAttendance(id: Int, userId: Int, locationId: Int, checkInTime: Date, checkOutTime: Date?, status: String) {
        attendanceDao.updateAttendance(id, userId, locationId, checkInTime, checkOutTime, status)
    }
}
