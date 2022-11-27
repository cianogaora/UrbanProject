package com.example.urbancomputing

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AirPressureActivity : AppCompatActivity() {

    lateinit var lineGraphView: GraphView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_air_pressure)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        lineGraphView = findViewById(R.id.idGraphView)
        val extras = intent.extras
        val date = extras?.getString("date")
        Log.d("pres", "$date")
        var hourlyStats = arrayOf<DataPoint>()
        val statsList = hourlyStats.toMutableList()
        val ref = Firebase.database.reference
        var j = 0
        if (date != null) {
            var series: LineGraphSeries<DataPoint>
            for(i in 0..8 step 2){
                ref.child("hourly-weather/$date/0$i:00:00").get().addOnSuccessListener {
                    it.children.associate { it.key.toString() to it.value.toString() }["pres"]
                        ?.let {
                                it1 -> statsList.add(DataPoint(j.toDouble(), it1.toDouble()))
                                Log.d("pres", it1)
                                Log.d("pres", hourlyStats.toString())
                        }

                }
                j += 1
            }
            for(i in 10..22 step 2){
                ref.child("hourly-weather/$date/$i:00:00").get().addOnSuccessListener {
                    it.children.associate { it.key.toString() to it.value.toString() }["pres"]
                        ?.let { it1 -> statsList.add(DataPoint(j.toDouble(), it1.toDouble()))
                            Log.d("pres", "${statsList.size}")}
                    if(statsList.size == 12){
                        Log.d("pres", "creating graph")
                        hourlyStats = statsList.toTypedArray()
                        series = LineGraphSeries(hourlyStats)
                        lineGraphView.animate()

                        // on below line we are setting scrollable
                        // for point graph view
                        lineGraphView.viewport.isScrollable = true

                        // on below line we are setting scalable.
                        lineGraphView.viewport.isScalable = true

                        // on below line we are setting scalable y
                        lineGraphView.viewport.setScalableY(true)

                        // on below line we are setting scrollable y
                        lineGraphView.viewport.setScrollableY(true)

                        // on below line we are setting color for series.
                        series.color = R.color.purple_200

                        // on below line we are adding
                        // data series to our graph view.
                        lineGraphView.addSeries(series)
                }

                }
            }

        }
        Log.d("pres", "stats: $hourlyStats")
    }
}