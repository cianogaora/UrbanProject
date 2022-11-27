package com.example.urbancomputing

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class WeeklyAverageOptionsActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekly_average_options)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val temp = findViewById<Button>(R.id.weeklyTemp)
        val pres = findViewById<Button>(R.id.weeklyAirPressure)
        val prcp = findViewById<Button>(R.id.weeklyPrecipitation)
        val wdir = findViewById<Button>(R.id.weeklyWindDir)
        val wspd = findViewById<Button>(R.id.weeklyWindSpeed)
        val extras = intent.extras
        if (extras != null) {
            //val dateText = findViewById<TextView>(R.id.dateTime)
            val date = extras.getString("dateDay")

//            dateText.text = "$date, $hourText:00:00"
//            Log.d("singleHour", "date: $date, hour: $hourText")
            temp.setOnClickListener {
                val change = Intent(this@WeeklyAverageOptionsActivity, WeeklyTempGraph::class.java)
                change.putExtra("date", date)
                startActivity(change)
            }
            pres.setOnClickListener {
                val change = Intent(this@WeeklyAverageOptionsActivity, WeeklyAirPressureGraph::class.java)
                Log.d("pres", "button clicked")
                Log.d("options", "$date")
                change.putExtra("date", date)
                startActivity(change)
            }
            prcp.setOnClickListener {
                val change = Intent(this@WeeklyAverageOptionsActivity, WeeklyPrecipitationGraph::class.java)

                change.putExtra("date", date)
                startActivity(change)
            }
            wdir.setOnClickListener {
                val change = Intent(this@WeeklyAverageOptionsActivity, WeeklyWindDirGraph::class.java)
                change.putExtra("date", date)
                startActivity(change)
            }
            wspd.setOnClickListener {
                val change = Intent(this@WeeklyAverageOptionsActivity, WeeklyWindSpeedGraph::class.java)
                change.putExtra("date", date)
                startActivity(change)
            }

        }
    }
}