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
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


import android.content.Context
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF


class AirPressureActivity : AppCompatActivity() {

    lateinit var lineGraphView: GraphView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_air_pressure)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        lineGraphView = findViewById(R.id.idGraphView)
        val lineChart = findViewById<LineChart>(R.id.lineChart)
        val extras = intent.extras
        val date = extras?.getString("date")
        Log.d("pres", "$date")
        var hourlyStats = arrayOf<DataPoint>()
        val entries = ArrayList<Entry>()
        val statsList = hourlyStats.toMutableList()
        val ref = Firebase.database.reference
        var j = 0
        var series: LineGraphSeries<DataPoint>
        if (date != null) {

            for(i in 0..22 step 2){
                if(i < 10){

                    ref.child("hourly-weather/$date/0$i:00:00").get().addOnSuccessListener {
                        it.children.associate { it.key.toString() to it.value.toString() }["pres"]
                            ?.let {
                                    it1 -> statsList.add(DataPoint(j.toDouble(), it1.toDouble()))
                                entries.add(Entry(j.toFloat()*2, it1.toFloat()))
                                j +=1
                                Log.d("pres", it1)
                                Log.d("pres", hourlyStats.toString())
                            }

                    }
                }
                else{
                    ref.child("hourly-weather/$date/$i:00:00").get().addOnSuccessListener {
                        it.children.associate { it.key.toString() to it.value.toString() }["pres"]
                            ?.let {
                                    it1 -> statsList.add(DataPoint(j.toDouble(), it1.toDouble()))
                                entries.add(Entry(j.toFloat()*2, it1.toFloat()))
                                j +=1
                                Log.d("pres", it1)
                                Log.d("pres", hourlyStats.toString())
                            }
                        if(entries.size == 12){
                            val vl = LineDataSet(entries, "My Type")

//Part4
                            vl.setDrawValues(false)
                            vl.setDrawFilled(true)
                            vl.lineWidth = 3f
                            vl.fillColor = R.color.colorLight
                            vl.fillAlpha = R.color.purple_700

                            lineChart.xAxis.labelRotationAngle = 0f

//Part6
                            lineChart.data = LineData(vl)

//Part7
                            lineChart.axisRight.isEnabled = false
                            lineChart.xAxis.axisMaximum = j*2+0.1f

//Part8
                            lineChart.setTouchEnabled(true)
                            lineChart.setPinchZoom(true)

//Part9
                            lineChart.description.text = "Days"
                            lineChart.setNoDataText("No forex yet!")

//Part10
                            lineChart.animateX(1800, Easing.EaseInExpo)

//Part11
//                            val markerView = CustomMarker(this@ShowForexActivity, R.layout.marker_view)
//                            lineChart.marker = markerView
//                            Log.d("pres", "creating graph")
//                            hourlyStats = statsList.toTypedArray()
//                            series = LineGraphSeries(arrayOf(
//                                DataPoint(1.0, 2.0),
//                                        DataPoint(2.0, 3.0)
//                            ))
//                            lineGraphView.animate()
//
//                            // on below line we are setting scrollable
//                            // for point graph view
//                            lineGraphView.viewport.isScrollable = true
//
//                            // on below line we are setting scalable.
//                            lineGraphView.viewport.isScalable = true
//
//                            // on below line we are setting scalable y
//                            lineGraphView.viewport.setScalableY(true)
//
//                            // on below line we are setting scrollable y
//                            lineGraphView.viewport.setScrollableY(true)
//
//                            // on below line we are setting color for series.
//                            series.color = R.color.purple_200
//
//                            // on below line we are adding
//                            // data series to our graph view.
//                            lineGraphView.addSeries(series)
//
                        }
                        }
                }
            }
        }
        Log.d("pres", "stats: $hourlyStats")
    }
}