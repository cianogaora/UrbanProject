package com.example.urbancomputing

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

        hourlyButton.setOnClickListener{
            val change = Intent(this@HourlyActivity, HourlyBreakdownActivity::class.java)
            startActivity(change)
        }
        singleHourButton.setOnClickListener {

        }
    }
}