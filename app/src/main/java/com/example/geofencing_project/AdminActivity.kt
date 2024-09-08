package com.example.geofencing_project

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.geofencing_project.admin_employee.AppDatabase
import com.example.geofencing_project.database.users.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdminActivity : AppCompatActivity() {

    private lateinit var employeeIdEditText: EditText
    private lateinit var checkEmployeeButton: Button
    private lateinit var chooseLocationButton: Button
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        // Initialize UI elements
        employeeIdEditText = findViewById(R.id.employeeIdEditText)
        checkEmployeeButton = findViewById(R.id.checkEmployeeButton)
        chooseLocationButton = findViewById(R.id.chooseLocationButton)

        // Get the UserDao from the Room database
        val database = AppDatabase.getDatabase(this)
        userDao = database.userDao()

        // Set up button click listener to check if employee ID is valid
        checkEmployeeButton.setOnClickListener {
            val employeeIdText = employeeIdEditText.text.toString()
            if (employeeIdText.isNotEmpty()) {
                val employeeId = employeeIdText.toInt()
                checkEmployeeExists(employeeId)
            } else {
                Toast.makeText(this, "Please enter a valid Employee ID", Toast.LENGTH_SHORT).show()
            }
        }

        // Set up button click listener for choosing location
        chooseLocationButton.setOnClickListener {
            // Logic to choose location (could open another activity or map fragment)
            Toast.makeText(this, "Location selection logic goes here", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkEmployeeExists(employeeId: Int) {
        lifecycleScope.launch {
            // Use background thread to check if employee exists in the database
            val employeeExists = withContext(Dispatchers.IO) {
                userDao.getUserByIdAndRole(employeeId, "user") != null
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
}
