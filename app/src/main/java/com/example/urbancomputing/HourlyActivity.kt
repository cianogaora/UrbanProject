package com.example.urbancomputing

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HourlyActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hourly)

        val homeButton = findViewById<Button>(R.id.homeButton)
        homeButton.setOnClickListener {
            val change = Intent(this@HourlyActivity, MainActivity::class.java)
            startActivity(change)
        }
    }
}