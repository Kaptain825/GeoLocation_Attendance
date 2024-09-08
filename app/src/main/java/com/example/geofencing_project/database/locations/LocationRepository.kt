package com.example.geofencing_project.database.locations

import androidx.lifecycle.LiveData

class LocationRepository(private val locationDao: LocationDao) {

    // Method to get all locations from the database
    fun getAllLocations(): LiveData<List<Location>> {
        return locationDao.getAllLocations()
    }

    // Method to insert a new location
    suspend fun insertLocation(location: Location): Long {
        return locationDao.insertLocation(location)
    }

    // Method to get a location by its ID
    suspend fun getLocationById(id: Int): Location? {
        return locationDao.getLocationById(id)
    }

    // Method to delete a location by its ID
    suspend fun deleteLocation(id: Int) {
        locationDao.deleteLocation(id)
    }

    // Method to update a location's details
    suspend fun updateLocation(id: Int, locationName: String, latitude: Double, longitude: Double, radius: Int) {
        locationDao.updateLocation(id, locationName, latitude, longitude, radius)
    }
}
