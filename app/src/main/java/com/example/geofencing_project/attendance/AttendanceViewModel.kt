package com.example.geofencing_project.attendance

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.Date

class AttendanceViewModel(private val attendanceRepository: AttendanceRepository) : ViewModel() {

    // LiveData to observe all attendance records
    val allAttendances: LiveData<List<Attendance>> = attendanceRepository.allAttendance

    // Method to insert an attendance record
    fun insertAttendance(attendance: Attendance) = viewModelScope.launch {
        attendanceRepository.insertAttendance(attendance)
    }

    // Method to get an attendance record by its ID
    suspend fun getAttendanceById(id: Int): Attendance? {
        return attendanceRepository.getAttendanceById(id)
    }

    // Method to delete an attendance record by its ID
    fun deleteAttendance(id: Int) = viewModelScope.launch {
        attendanceRepository.deleteAttendance(id)
    }

    // Method to update an attendance record
    fun updateAttendance(id: Int, userId: Int, locationId: Int, checkInTime: Date, checkOutTime: Date?, status: String) = viewModelScope.launch {
        attendanceRepository.updateAttendance(id, userId, locationId, checkInTime, checkOutTime, status)
    }
}
