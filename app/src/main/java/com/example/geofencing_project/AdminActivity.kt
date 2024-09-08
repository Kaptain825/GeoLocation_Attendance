package com.example.geofencing_project

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.geofencing_project.admin_employee.AdminEmployeeViewModel
import com.example.geofencing_project.admin_employee.AdminEmployeeRepository
import com.example.geofencing_project.admin_employee.AdminEmployeeViewModelFactory
import com.example.geofencing_project.admin_employee.AppDatabase
import com.example.geofencing_project.database.locations.Location
import com.example.geofencing_project.database.locations.LocationRepository
import com.example.geofencing_project.database.locations.LocationViewModel
import com.example.geofencing_project.LocationViewModelFactory
import com.example.geofencing_project.database.users.UserDao
import com.example.geofencing_project.database.users.UserRepository
import com.example.geofencing_project.database.users.UserViewModel
import com.example.geofencing_project.UserViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdminActivity : AppCompatActivity() {
    // UI Elements
    private lateinit var employeeIdEditText: EditText
    private lateinit var checkEmployeeButton: Button
    private lateinit var chooseLocationButton: Button
    private lateinit var locationNameEditText: EditText
    private lateinit var latitudeEditText: EditText
    private lateinit var longitudeEditText: EditText
    private lateinit var radiusEditText: EditText

    // ViewModels
    private lateinit var locationViewModel: LocationViewModel
    private lateinit var adminEmployeeViewModel: AdminEmployeeViewModel
    private lateinit var userViewModel: UserViewModel

    // Store username
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        // Retrieve the username from the Intent
        username = intent.getStringExtra("USERNAME")

        // Initialize UI elements
        employeeIdEditText = findViewById(R.id.employeeIdEditText)
        checkEmployeeButton = findViewById(R.id.checkEmployeeButton)
        chooseLocationButton = findViewById(R.id.chooseLocationButton)
        locationNameEditText = findViewById(R.id.locationNameEditText)
        latitudeEditText = findViewById(R.id.latitudeEditText)
        longitudeEditText = findViewById(R.id.longitudeEditText)
        radiusEditText = findViewById(R.id.radiusEditText)

        // Get the ViewModels from the Room database
        val database = AppDatabase.getDatabase(this)
        val locationDao = database.locationDao()
        val locationRepository = LocationRepository(locationDao)
        locationViewModel = ViewModelProvider(this, LocationViewModelFactory(locationRepository)).get(LocationViewModel::class.java)

        val adminEmployeeDao = database.adminEmployeeDao()
        val adminEmployeeRepository = AdminEmployeeRepository(adminEmployeeDao)
        adminEmployeeViewModel = ViewModelProvider(this, AdminEmployeeViewModelFactory(adminEmployeeRepository)).get(AdminEmployeeViewModel::class.java)

        val userDao = database.userDao()
        val userRepository = UserRepository(userDao)
        userViewModel = ViewModelProvider(this, UserViewModelFactory(userRepository)).get(UserViewModel::class.java)

        // Set up button click listener to check if employee ID is valid
        checkEmployeeButton.setOnClickListener {
            val employeeIdText = employeeIdEditText.text.toString()
            if (employeeIdText.isNotEmpty()) {
                val employeeId = employeeIdText.toIntOrNull()
                if (employeeId != null) {
                    checkEmployeeExists(employeeId)
                } else {
                    Toast.makeText(this, "Please enter a valid Employee ID", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter a valid Employee ID", Toast.LENGTH_SHORT).show()
            }
        }

        // Set up button click listener for choosing location
        chooseLocationButton.setOnClickListener {
            val locationName = locationNameEditText.text.toString()
            val latitude = latitudeEditText.text.toString().toDoubleOrNull()
            val longitude = longitudeEditText.text.toString().toDoubleOrNull()
            val radius = radiusEditText.text.toString().toIntOrNull() ?: 200 // Default radius of 200 meters

            if (locationName.isNotEmpty() && latitude != null && longitude != null) {
                insertAndUpdateLocation(locationName, latitude, longitude, radius)
            } else {
                Toast.makeText(this, "Please fill in all fields correctly.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkEmployeeExists(employeeId: Int) {
        lifecycleScope.launch {
            // Use background thread to check if employee exists in the database
            val employeeExists = withContext(Dispatchers.IO) {
                userViewModel.getUserByIdAndRole(employeeId, "user").value != null
            }

            if (employeeExists) {
                // If the employee exists, enable the choose location button
                chooseLocationButton.isEnabled = true
                Toast.makeText(this@AdminActivity, "Employee found. You can now choose a location.", Toast.LENGTH_SHORT).show()
            } else {
                // If the employee does not exist, show an error message
                chooseLocationButton.isEnabled = false
                Toast.makeText(this@AdminActivity, "Employee not found. Please enter a valid ID.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun insertAndUpdateLocation(locationName: String, latitude: Double, longitude: Double, radius: Int) {
        lifecycleScope.launch {
            val location = Location(location_name = locationName, latitude = latitude, longitude = longitude, radius = radius)

            val locationId = withContext(Dispatchers.IO) {
                locationViewModel.insertLocation(location)
                // Fetch the newly inserted location ID
                locationViewModel.getLocationById(location.location_id)?.location_id
            }

            if (locationId != null) { // Check if insertion was successful
                val employeeIdText = employeeIdEditText.text.toString()
                val employeeId = employeeIdText.toIntOrNull()

                if (employeeId != null) {
                    updateAdminEmployeeWithLocation(employeeId, locationId.toInt())
                } else {
                    Toast.makeText(this@AdminActivity, "Please enter a valid Employee ID.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@AdminActivity, "Location insertion failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateAdminEmployeeWithLocation(employeeId: Int, locationId: Int) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                // Fetch the AdminEmployee record to update
                val adminEmployee = adminEmployeeViewModel.getAdminByEmployee(employeeId).value
                if (adminEmployee != null) {
                    // Update the location_id in AdminEmployee table
                    adminEmployeeViewModel.updateLocationId(employeeId, locationId)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@AdminActivity, "AdminEmployee updated with new location.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@AdminActivity, "AdminEmployee record not found.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}