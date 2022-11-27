package com.example.urbancomputing

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class SingleHourActivity : AppCompatActivity(){

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single_hour_stats)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if (extras != null) {
            val dateTime = findViewById<TextView>(R.id.dateTime)
            val date = extras.getString("date")
            var hour = extras.getString("hour")
            dateTime.text = "$date, $hour:00:00"
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
            //var stats = mapOf<String, String>()
            val statBox = findViewById<TextView>(R.id.stats)
            val ref = Firebase.database.reference
            ref.child("hourly-weather/$date/$hour:00:00").get().addOnSuccessListener { it ->
                val stats = it.children.associate { it.key.toString() to it.value.toString() }
                Log.d("singleHour", "Data received $stats")
                val text = "Weather Condition Code: \t ${stats["coco"]} \n" +
                        "Dew Point: \t ${stats["dwpt"]}°C Td \n" +
                        "Total Precipitation: \t ${stats["prcp"]} mm \n" +
                        "Air Pressure: \t ${stats["pres"]}hPa \n" +
                        "Humidity: \t %${stats["rhum"]} \n" +
                        "Snow: \t ${stats["snow"]} \n" +
                        "Temperature: \t ${stats["temp"]}°C \n" +
                        "Total Sunshine: \t ${stats["tsun"]} mins \n" +
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