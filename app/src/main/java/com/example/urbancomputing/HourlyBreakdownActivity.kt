package com.example.urbancomputing

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class HourlyBreakdownActivity : AppCompatActivity(){
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hourly_breakdown)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val temp = findViewById<Button>(R.id.temperature)
        val pres = findViewById<Button>(R.id.airPressure)
        val dwpt = findViewById<Button>(R.id.dewPoint)
        val prcp = findViewById<Button>(R.id.precipitation)
        val wdir = findViewById<Button>(R.id.windDir)
        val wspd = findViewById<Button>(R.id.windSpeed)
        val extras = intent.extras
        if (extras != null) {
            val dateText = findViewById<TextView>(R.id.dateTime)
            val date = extras.getString("date")

//            dateText.text = "$date, $hourText:00:00"
//            Log.d("singleHour", "date: $date, hour: $hourText")

            temp.setOnClickListener {
                val change = Intent(this@HourlyBreakdownActivity, TempGraphActivity::class.java)
                change.putExtra("date", date)
                startActivity(change)
            }
            pres.setOnClickListener {
                val change = Intent(this@HourlyBreakdownActivity,AirPressureActivity::class.java)
                Log.d("pres", "button clicked")
                change.putExtra("date", date)
                startActivity(change)
            }
            dwpt.setOnClickListener {
                val change = Intent(this@HourlyBreakdownActivity,DewPointActivity::class.java)
                change.putExtra("date", date)
                startActivity(change)
            }
            prcp.setOnClickListener {
                val change = Intent(this@HourlyBreakdownActivity,PrecipitationActivity::class.java)
                change.putExtra("date", date)
                startActivity(change)
            }
            wdir.setOnClickListener {
                val change = Intent(this@HourlyBreakdownActivity,WindDirectionActivity::class.java)
                change.putExtra("date", date)
                startActivity(change)
            }
            wspd.setOnClickListener {
                val change = Intent(this@HourlyBreakdownActivity,WindSpeedActivity::class.java)
                change.putExtra("date", date)
                startActivity(change)
            }

        }
    }
}