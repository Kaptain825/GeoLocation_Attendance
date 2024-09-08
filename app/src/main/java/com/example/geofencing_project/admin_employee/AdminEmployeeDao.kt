package com.example.geofencing_project.admin_employee

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AdminEmployeeDao {
    @Insert
    suspend fun insert(adminEmployee: AdminEmployee)

    @Query("SELECT * FROM AdminEmployee WHERE admin_id = :adminId")
    suspend fun getEmployeesByAdmin(adminId: Int): List<AdminEmployee>

    @Query("SELECT * FROM AdminEmployee WHERE employee_id = :employeeId")
    suspend fun getAdminByEmployee(employeeId: Int): AdminEmployee?
}