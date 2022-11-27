package com.example.urbancomputing

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DailyAverageActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_average)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val extras = intent.extras
        if (extras != null) {
            Log.d("daily", "date from extras ${extras.getString("dateDay").toString()}")
        }
        if (extras != null) {
            val date = extras.getString("dateDay")
            Log.d("daily", "date: $date")
            val dateTitle = findViewById<TextView>(R.id.dateDay)
            dateTitle.text = date.toString()

            val statBox = findViewById<TextView>(R.id.dailyStats)
            val ref = Firebase.database.reference
            ref.child("daily-weather/$date").get().addOnSuccessListener { it ->
                val stats = it.children.associate { it.key.toString() to it.value.toString() }
                Log.d("daily", "Data received $stats")
                val text = "Total Precipitation: \t ${stats["prcp"]} mm \n" +
                        "Air Pressure: \t ${stats["pres"]}hPa \n" +
                        "Snow: \t ${stats["snow"]} \n" +
                        "Temperature: \t ${stats["tavg"]}°C \n" +
                        "Max Temp: \t ${stats["tmax"]} \n"+
                        "Min Temp: \t ${stats["tmin"]} \n" +
                        "Wind Direction: \t °${stats["wdir"]} \n" +
                        "Wind Peak: \t ${stats["wpgt"]} km/h\n" +
                        "Wind Speed: \t ${stats["wspd"]} km/h\n"
                statBox.text = text
            }


        }
        else{
            Log.d("singleHour", "invalid input")
        }
    }
}