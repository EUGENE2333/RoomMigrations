package com.anushka.roommigrationdemo1

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.migrations.Student

@Dao
interface StudentDAO {

    @Insert
    suspend fun insertStudent(student: Student)

}
