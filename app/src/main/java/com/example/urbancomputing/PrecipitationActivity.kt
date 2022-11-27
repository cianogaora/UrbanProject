package com.example.urbancomputing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.database.ktx.database

class PrecipitationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_precipitation)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        lineGraphView = findViewById(R.id.idGraphView)
        val lineChart = findViewById<LineChart>(R.id.lineChart)
        val extras = intent.extras
        val date = extras?.getString("date")
        Log.d("pres", "$date")
        //var hourlyStats = arrayOf<DataPoint>()
        val entries = ArrayList<Entry>()
        //val statsList = hourlyStats.toMutableList()
        val ref = Firebase.database.reference
        var j = 0
        //var series: LineGraphSeries<DataPoint>
        if (date != null) {

            for(i in 0..22 step 2){
                if(i < 10){

                    ref.child("hourly-weather/$date/0$i:00:00").get().addOnSuccessListener {
                        it.children.associate { it.key.toString() to it.value.toString() }["prcp"]
                            ?.let {
                                    it1 -> //statsList.add(DataPoint(j.toDouble(), it1.toDouble()))
                                entries.add(Entry(j.toFloat()*2, it1.toFloat()))
                                j +=1
                                Log.d("pres", it1)
                                //Log.d("pres", hourlyStats.toString())
                            }

                    }
                }
                else{
                    ref.child("hourly-weather/$date/$i:00:00").get().addOnSuccessListener {
                        it.children.associate { it.key.toString() to it.value.toString() }["prcp"]
                            ?.let {
                                    it1 -> //statsList.add(DataPoint(j.toDouble(), it1.toDouble()))
                                entries.add(Entry(j.toFloat()*2, it1.toFloat()))
                                j +=1
                                Log.d("pres", it1)
                                //Log.d("pres", hourlyStats.toString())
                            }
                        if(entries.size == 12){
                            val vl = LineDataSet(entries, "Precipitation (mm)")

                            vl.setDrawValues(false)
                            vl.setDrawFilled(true)
                            vl.lineWidth = 3f
                            vl.fillColor = R.color.black
                            vl.fillAlpha = R.color.purple_700

                            lineChart.xAxis.labelRotationAngle = 0f

                            lineChart.data = LineData(vl)

                            lineChart.axisRight.isEnabled = false
                            lineChart.xAxis.axisMaximum = j*2+0.1f

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