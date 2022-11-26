package com.example.urbancomputing

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SingleHourActivity : AppCompatActivity(){
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hourly_breakdown)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if (extras != null) {
            val date = extras.getString("date")
            var hour = extras.getString("hour")
            Log.d("singleHour", "date: $date, hour: $hour")

            var modHour = hour?.toInt()
            if (modHour != null) {
                if (modHour.mod(2) != 0){
                    modHour -= 1
                    if (modHour < 0) {
                        modHour += 1
                    }
                }
            }
            hour = modHour.toString()
            if(hour.length == 1){
                hour = "0$hour"
            }
            //2022-01-01
            val ref = Firebase.database.reference
            ref.child("hourly-weather/$date/$hour:00:00").get().addOnSuccessListener { it ->
                val stats = it.children.associate { it.key.toString() to it.value.toString() }
                Log.d("singleHour", "Data received $stats")

            }
        }
        else{
            Log.d("singleHour", "invalid input")
        }
    }
}