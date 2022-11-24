package com.example.urbancomputing

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    @SuppressLint("UseSupportActionBar")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ref = FirebaseDatabase.getInstance().reference
        val database = Firebase.database.reference
        val dailyButton = findViewById<Button>(R.id.daily)
        val hourlyButton = findViewById<Button>(R.id.hourly)
        var date = "2022-01-01"
        var time = "00:00:00"
        var data: Map<String, String>
        dailyButton.setOnClickListener {
            val change = Intent(this@MainActivity, DailyActivity::class.java)
            startActivity(change)
        }
        hourlyButton.setOnClickListener {
            val change = Intent(this@MainActivity, HourlyActivity::class.java)
            startActivity(change)
        }
        ref.child("hourly-weather/$date/$time").get().addOnSuccessListener { it ->
            data = it.children.associate { it.key.toString() to it.value.toString() }

            Log.i("test", "Got value $data")
//            var data = it.children.map{it.key to it.value}.toMap()
//            Log.i("test", "Got value ${data[0]}")
        }
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

    }

}