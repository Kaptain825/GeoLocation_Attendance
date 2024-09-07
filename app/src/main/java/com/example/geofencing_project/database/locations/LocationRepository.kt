package com.example.geofencing_project.database.locations

import androidx.lifecycle.LiveData

class LocationRepository(private val locationDao: LocationDao) {

    // LiveData to observe all locations
    val allLocations: LiveData<List<Location>> = locationDao.getAllLocations()

    // Insert a location into the database
    suspend fun insertLocation(location: Location) {
        locationDao.insertLocation(location)
    }

    // Get a location by its ID
    suspend fun getLocationById(id: Int): Location? {
        return locationDao.getLocationById(id)
    }

    // Delete a location by its ID
    suspend fun deleteLocation(id: Int) {
        locationDao.deleteLocation(id)
    }

    // Update a location
    suspend fun updateLocation(id: Int, locationName: String, latitude: Double, longitude: Double, radius: Int) {
        locationDao.updateLocation(id, locationName, latitude, longitude, radius)
    }
}
