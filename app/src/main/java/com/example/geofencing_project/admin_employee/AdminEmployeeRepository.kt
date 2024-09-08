package com.example.geofencing_project.admin_employee

import com.example.geofencing_project.admin_employee.AdminEmployee
import com.example.geofencing_project.admin_employee.AdminEmployeeDao

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
}