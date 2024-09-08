package com.example.geofencing_project.admin_employee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AdminEmployeeViewModelFactory(private val repository: AdminEmployeeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdminEmployeeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AdminEmployeeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
