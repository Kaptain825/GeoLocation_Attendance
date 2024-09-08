package com.example.geofencing_project.database.locations
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class Location(
    @PrimaryKey(autoGenerate = true)
    val location_id: Int = 0,
    val location_name: String,
    val latitude: Double,
    val longitude: Double,
    val radius:Int
)