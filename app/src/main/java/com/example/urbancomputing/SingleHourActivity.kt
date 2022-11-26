package com.example.urbancomputing

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class SingleHourActivity : AppCompatActivity(){
    @SuppressLint("MissingInflatedId")

    lateinit var barChart: BarChart
    lateinit var barData: BarData
    lateinit var barDataSet: BarDataSet
    lateinit var barEntriesList: ArrayList<BarEntry>

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
            var stats: Map<String, String>
            val ref = Firebase.database.reference
            ref.child("hourly-weather/$date/$hour:00:00").get().addOnSuccessListener { it ->
                stats = it.children.associate { it.key.toString() to it.value.toString() }
                Log.d("singleHour", "Data received $stats")

            }
            barChart = findViewById(R.id.idBarChart)
            getBarChartData()
            barDataSet = BarDataSet(barEntriesList, "Bar Chart Data")
            barData = BarData(barDataSet)
            barChart.data = barData
            barDataSet.valueTextColor = Color.BLACK
            barDataSet.setColor(resources.getColor(R.color.purple_200))
            barDataSet.valueTextSize = 16f
            barChart.description.isEnabled = false
        }
        else{
            Log.d("singleHour", "invalid input")
        }
    }
    private fun getBarChartData() {
        barEntriesList = ArrayList()
        barEntriesList.add(BarEntry(1f, 1f))
        barEntriesList.add(BarEntry(2f, 2f))
        barEntriesList.add(BarEntry(3f, 3f))
        barEntriesList.add(BarEntry(4f, 4f))
        barEntriesList.add(BarEntry(5f, 5f))
        }
}