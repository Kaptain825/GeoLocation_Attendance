package com.example.geofencing_project

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent

class GeofenceBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val geofencingEvent = GeofencingEvent.fromIntent(intent)

        if (geofencingEvent != null) {
            if (geofencingEvent.hasError()) {
                return
            }
        }

        val geofenceTransition = geofencingEvent?.geofenceTransition

        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
            // Inside geofence, update status to Present
            updateAttendanceStatus(context, "Present")
        } else if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {
            // Outside geofence, update status to Absent
            updateAttendanceStatus(context, "Absent")
        }
    }

    private fun updateAttendanceStatus(context: Context, status: String) {
        // Update the user's attendance status in the database
        // You can access the ViewModel here and update the User entity status
        Toast.makeText(context, "Attendance status updated to: $status", Toast.LENGTH_SHORT).show()
    }
}
