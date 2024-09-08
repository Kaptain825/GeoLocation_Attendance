package com.example.geofencing_project

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

class GeofenceHelper(private val context: Context) {

    private lateinit var geofencingClient: GeofencingClient
    private val geofenceList: MutableList<Geofence> = ArrayList()

    fun setupGeofencing(adminLatitude: Double, adminLongitude: Double, radius: Float = 200f) {
        geofencingClient = LocationServices.getGeofencingClient(context)

        // Create a geofence
        val geofence = Geofence.Builder()
            .setRequestId("admin_location")
            .setCircularRegion(
                adminLatitude,
                adminLongitude,
                radius // Radius in meters (200m)
            )
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT)
            .build()

        geofenceList.add(geofence)

        // Create a geofencing request
        val geofencingRequest = GeofencingRequest.Builder()
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            .addGeofence(geofence)
            .build()

        // Add the geofence
        geofencingClient.addGeofences(geofencingRequest, getGeofencePendingIntent())
    }

    private fun getGeofencePendingIntent(): PendingIntent {
        // Setup PendingIntent for geofence transitions
        val intent = Intent(context, GeofenceBroadcastReceiver::class.java)
        return PendingIntent.getBroadcast(
            context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )
    }
}
