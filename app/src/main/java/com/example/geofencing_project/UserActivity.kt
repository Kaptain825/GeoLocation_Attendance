package com.example.geofencing_project

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        // Get the username from the Intent
        val username = intent.getStringExtra("USERNAME")

        if (username != null) {
            // Display a welcome message or use the username
            Toast.makeText(this, "Welcome, $username!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Error: Username not found!", Toast.LENGTH_SHORT).show()
        }
    }
}