package com.example.geofencing_project.database.locations

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LocationDao {

    @Insert
    suspend fun insertLocation(location: Location)

    @Query("SELECT * FROM locations")
    fun getAllLocations(): LiveData<List<Location>>

    @Query("SELECT * FROM locations WHERE location_id = :id LIMIT 1")
    suspend fun getLocationById(id: Int): Location?

    @Query("DELETE FROM locations WHERE location_id = :id")
    suspend fun deleteLocation(id: Int)

    @Query("UPDATE locations SET location_name = :locationName, latitude = :latitude, longitude = :longitude, radius = :radius WHERE location_id = :id")
    suspend fun updateLocation(id: Int, locationName: String, latitude: Double, longitude: Double, radius: Int)
}
