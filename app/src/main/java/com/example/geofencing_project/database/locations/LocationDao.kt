package com.example.geofencing_project.database.locations

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface LocationDao {

    @Query("SELECT * FROM locations")
    fun getAllLocations(): LiveData<List<Location>>

    @Insert
    suspend fun insertLocation(location: Location): Long

    @Query("SELECT * FROM locations WHERE location_id = :id")
    suspend fun getLocationById(id: Int): Location?

    @Query("DELETE FROM locations WHERE location_id = :id")
    suspend fun deleteLocation(id: Int)

    @Query("UPDATE locations SET location_name = :locationName, latitude = :latitude, longitude = :longitude, radius = :radius WHERE id = :id")
    suspend fun updateLocation(id: Int, locationName: String, latitude: Double, longitude: Double, radius: Int)
}
