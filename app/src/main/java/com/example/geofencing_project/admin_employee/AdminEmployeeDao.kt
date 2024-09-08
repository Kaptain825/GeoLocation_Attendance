package com.example.geofencing_project.admin_employee

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow  // Import this for reactive stream support

@Dao
interface AdminEmployeeDao {
    @Insert
    suspend fun insert(adminEmployee: AdminEmployee)

    @Query("SELECT * FROM AdminEmployee WHERE admin_id = :adminId")
    suspend fun getEmployeesByAdmin(adminId: Int): List<AdminEmployee>

    @Query("SELECT * FROM AdminEmployee WHERE employee_id = :employeeId")
    suspend fun getAdminByEmployee(employeeId: Int): AdminEmployee?

    @Query("SELECT * FROM AdminEmployee WHERE location_id = :locationId")
    fun getAdminEmployeeByLocation(locationId: Int): Flow<List<AdminEmployee>>  // Use Flow for queries without suspend
}
