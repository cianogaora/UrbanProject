package com.example.urbancomputing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DailyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)

        val homeButton = findViewById<Button>(R.id.homeButtonDaily)
        homeButton.setOnClickListener {
            val change = Intent(this@DailyActivity, MainActivity::class.java)
            startActivity(change)
        }
    }
}