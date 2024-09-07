package com.example.geofencing_project.attendance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.geofencing_project.DateConverter // Import your DateConverter

@Database(entities = [Attendance::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class) // Add this annotation
abstract class AttendanceRoomDatabase : RoomDatabase() {

    abstract fun attendanceDao(): AttendanceDao

    companion object {
        @Volatile
        private var INSTANCE: AttendanceRoomDatabase? = null

        fun getDatabase(context: Context): AttendanceRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AttendanceRoomDatabase::class.java,
                    "attendance_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
