package com.example.geofencing_project.admin_employee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AdminEmployeeViewModel(private val repository: AdminEmployeeRepository) : ViewModel() {

    fun insert(adminEmployee: AdminEmployee) = viewModelScope.launch {
        repository.insert(adminEmployee)
    }

    fun getEmployeesByAdmin(adminId: Int): LiveData<List<AdminEmployee>> {
        val result = MutableLiveData<List<AdminEmployee>>()
        viewModelScope.launch {
            result.postValue(repository.getEmployeesByAdmin(adminId))
        }
        return result
    }

    fun getAdminByEmployee(employeeId: Int): LiveData<AdminEmployee?> {
        val result = MutableLiveData<AdminEmployee?>()
        viewModelScope.launch {
            result.postValue(repository.getAdminByEmployee(employeeId))
        }
        return result
    }

    fun updateLocationId(employeeId: Int, locationId: Int?) = viewModelScope.launch {
        repository.updateLocationId(employeeId, locationId)
    }

    fun getAdminEmployeeByLocation(locationId: Int): Flow<List<AdminEmployee>> {
        return repository.getAdminEmployeeByLocation(locationId)
    }
}
