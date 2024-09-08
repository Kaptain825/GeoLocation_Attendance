package com.example.geofencing_project.database.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LocationViewModel(private val locationRepository: LocationRepository) : ViewModel() {

    // Expose all locations as LiveData
    val allLocations: LiveData<List<Location>> = locationRepository.getAllLocations()

    // Method to insert a new location and return its ID
    suspend fun insertLocation(location: Location): Long {
        return locationRepository.insertLocation(location)
    }

    // Method to get a location by its ID
    suspend fun getLocationById(id: Int): Location? {
        return locationRepository.getLocationById(id)
    }

    // Method to delete a location by its ID
    fun deleteLocation(id: Int) = viewModelScope.launch {
        locationRepository.deleteLocation(id)
    }

    // Method to update a location's details
    fun updateLocation(id: Int, locationName: String, latitude: Double, longitude: Double, radius: Int) = viewModelScope.launch {
        locationRepository.updateLocation(id, locationName, latitude, longitude, radius)
    }
}
