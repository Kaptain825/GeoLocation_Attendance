package com.example.geofencing_project.attendance

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.Date

@Dao
interface AttendanceDao {

    @Insert
    suspend fun insertAttendance(attendance: Attendance)

    @Query("SELECT * FROM attendance")
    fun getAllAttendance(): LiveData<List<Attendance>>

    @Query("SELECT * FROM attendance WHERE attendance_id = :id LIMIT 1")
    suspend fun getAttendanceById(id: Int): Attendance?

    @Query("DELETE FROM attendance WHERE attendance_id = :id")
    suspend fun deleteAttendance(id: Int)

    @Query("UPDATE attendance SET user_id = :userId, location_id = :locationId, check_in_time = :checkInTime, check_out_time = :checkOutTime, status = :status WHERE attendance_id = :id")
    suspend fun updateAttendance(id: Int, userId: Int, locationId: Int, checkInTime: Date, checkOutTime: Date?, status: String)
}
