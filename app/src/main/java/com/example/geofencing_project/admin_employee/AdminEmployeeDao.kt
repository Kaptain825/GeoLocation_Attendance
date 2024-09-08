package com.example.geofencing_project.admin_employee

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AdminEmployeeDao {

    @Insert
    suspend fun insert(adminEmployee: AdminEmployee)

    @Query("SELECT * FROM admin_employee WHERE admin_id = :adminId")
    suspend fun getEmployeesByAdmin(adminId: Int): List<AdminEmployee>

    @Query("SELECT * FROM admin_employee WHERE employee_id = :employeeId")
    suspend fun getAdminByEmployee(employeeId: Int): AdminEmployee?

    @Query("UPDATE admin_employee SET location_id = :locationId WHERE employee_id = :employeeId")
    suspend fun updateLocationId(employeeId: Int, locationId: Int?)

    @Query("SELECT * FROM admin_employee WHERE location_id = :locationId")
    fun getAdminEmployeeByLocation(locationId: Int): Flow<List<AdminEmployee>>
}
