package com.example.geofencing_project.admin_employee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch

class AdminEmployeeViewModel(private val repository: AdminEmployeeRepository) : ViewModel() {

    fun insert(adminEmployee: AdminEmployee) = viewModelScope.launch {
        repository.insert(adminEmployee)
    }

    fun getEmployeesByAdmin(adminId: Int) = viewModelScope.launch {
        repository.getEmployeesByAdmin(adminId)
    }

    fun getAdminByEmployee(employeeId: Int) = viewModelScope.launch {
        repository.getAdminByEmployee(employeeId)
    }
}