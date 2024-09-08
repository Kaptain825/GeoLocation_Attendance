package com.example.geofencing_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.geofencing_project.database.users.User
import com.example.geofencing_project.database.users.UserDao
import com.example.geofencing_project.database.users.UserRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpActivity : AppCompatActivity() {

    private lateinit var userDao: UserDao // Declare UserDao here

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Initialize the UserDao
        val userRoomDatabase = UserRoomDatabase.getDatabase(this)
        userDao = userRoomDatabase.userDao()

        // Get references to input fields
        val nameField = findViewById<EditText>(R.id.et_signup_name)
        val emailField = findViewById<EditText>(R.id.et_signup_email)
        val phoneField = findViewById<EditText>(R.id.et_signup_phone)
        val passwordField = findViewById<EditText>(R.id.et_signup_password)
        val roleField = findViewById<EditText>(R.id.et_signup_role)

        // Sign Up button click handler
        val signUpButton = findViewById<Button>(R.id.btn_signup_confirm)
        signUpButton.setOnClickListener {
            val name = nameField.text.toString()
            val email = emailField.text.toString()
            val phone = phoneField.text.toString()
            val password = passwordField.text.toString()
            val role = roleField.text.toString()

            if (validateInputs(name, email, phone, password, role)) {
                // Insert user in the background
                lifecycleScope.launch {
                    insertUserToDatabase(name, email, phone, password, role)
                }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Validate inputs before inserting into the database
    private fun validateInputs(name: String, email: String, phone: String, password: String, role: String): Boolean {
        return name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty() && password.isNotEmpty() && role.isNotEmpty()
    }

    // Insert user into the database
    private suspend fun insertUserToDatabase(name: String, email: String, phone: String, password: String, role: String) {
        withContext(Dispatchers.IO) {
            val user = User(name = name, email = email, phone_no = phone, password = password, role = role)
            userDao.insert(user)

            withContext(Dispatchers.Main) {
                // Show a success message
                Toast.makeText(this@SignUpActivity, "User registered successfully", Toast.LENGTH_SHORT).show()

                // Navigate back to the login screen
                val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
