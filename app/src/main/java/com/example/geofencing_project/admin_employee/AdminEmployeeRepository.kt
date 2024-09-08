package com.example.geofencing_project.admin_employee

import kotlinx.coroutines.flow.Flow

class AdminEmployeeRepository(private val adminEmployeeDao: AdminEmployeeDao) {

    suspend fun insert(adminEmployee: AdminEmployee) {
        adminEmployeeDao.insert(adminEmployee)
    }

    suspend fun getEmployeesByAdmin(adminId: Int): List<AdminEmployee> {
        return adminEmployeeDao.getEmployeesByAdmin(adminId)
    }

    suspend fun getAdminByEmployee(employeeId: Int): AdminEmployee? {
        return adminEmployeeDao.getAdminByEmployee(employeeId)
    }

    fun getAdminEmployeeByLocation(locationId: Int): Flow<List<AdminEmployee>> {
        return adminEmployeeDao.getAdminEmployeeByLocation(locationId)
    }
}
