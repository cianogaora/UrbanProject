package com.example.urbancomputing

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.LocalDate

class WeeklyWindSpeedGraph : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekly_wind_speed_graph)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val ref = Firebase.database.reference
        val extras = intent.extras
        val date = extras?.getString("date")
        Log.d("AirP", "$date")
        var mutableDate = LocalDate.parse(date)
        val lineChart = findViewById<LineChart>(R.id.lineChart)
        val entries = ArrayList<Entry>()

        var days = ArrayList<String>()
        for(i in 1..7 step 1){
            days.add(mutableDate.minusDays(i.toLong()).toString())
            Log.d("AirP", "$days")
            if(days.size == 7){

                for (j in 0..6 step 1) {
                    ref.child("daily-weather/${days.get(j)}").get().addOnSuccessListener {
                        val pres = it.children.associate { it.key.toString() to it.value.toString() }
                        //Log.d("AirP", "$pres")
                        pres["wspd"]?.let { it1 -> Entry( (i - j).toFloat(), it1.toFloat()) }
                            ?.let { it2 -> entries.add(0, it2) }
                        Log.d("AirP", "$entries")
                        if(entries.size == 7){
                            val vl = LineDataSet(entries, "Wind Speed (km/h)")

                            vl.setDrawValues(false)
                            vl.setDrawFilled(true)
                            vl.lineWidth = 3f
                            vl.fillColor = R.color.black
                            vl.fillAlpha = R.color.purple_700

                            lineChart.xAxis.labelRotationAngle = 0f

                            lineChart.data = LineData(vl)

                            lineChart.axisRight.isEnabled = false
                            lineChart.xAxis.axisMaximum = j+0.1f

                            lineChart.setTouchEnabled(true)
                            lineChart.setPinchZoom(true)

                            lineChart.description.text = "Hours"


                            lineChart.animateX(1800, Easing.EaseInExpo)
                        }
                    }

                }
            }
        }
    }
}