package com.anushka.roommigrationdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.migrations.R
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dao = StudentDatabase.getInstance(application).subscriberDAO
        val nameEditText = findViewById<EditText>(R.id.etName)
        val courseNameText = findViewById<EditText>(R.id.etCourseName)
        val button = findViewById<Button>(R.id.btnSubmit)
        button.setOnClickListener {
            lifecycleScope.launch {
                nameEditText.text.let {
                    dao.insertStudent(Student(0,it.toString(),courseNameText.text.toString()))
                     nameEditText.setText("")
                    courseNameText.setText("")
                }
            }
        }


    }
}
