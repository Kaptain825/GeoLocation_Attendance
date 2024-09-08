package com.example.geofencing_project


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.geofencing_project.database.locations.LocationRepository
import com.example.geofencing_project.database.locations.LocationViewModel

class LocationViewModelFactory(private val repository: LocationRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LocationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
