package com.example.urbancomputing

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class HourlyActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hourly)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val hourlyButton = findViewById<Button>(R.id.hourly_button)
        val singleHourButton = findViewById<Button>(R.id.single_hour_button)
        val dateBox = findViewById<EditText>(R.id.dateBox)
        val hourBox = findViewById<EditText>(R.id.editTextTime)



        hourlyButton.setOnClickListener{
            val change = Intent(this@HourlyActivity, HourlyBreakdownActivity::class.java)
            val dateText = dateBox.text.toString()
            Log.d("inputs", "date input: $dateText")
            startActivity(change)
        }
        singleHourButton.setOnClickListener {
            val hourText = hourBox.text.toString()
            val dateText = dateBox.text.toString()
            Log.d("inputs", "date input: $dateText, hour input: $hourText")
            val change = Intent(this@HourlyActivity, SingleHourActivity::class.java)
            change.putExtra("hour", hourText)
            change.putExtra("date", dateText)
            startActivity(change)
        }
    }
}