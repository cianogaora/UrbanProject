package com.example.urbancomputing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DailyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val daily = findViewById<Button>(R.id.dailyAvg)



        daily.setOnClickListener {
            val date = findViewById<EditText>(R.id.dailyDateBox).text.toString()
            val intent = Intent(this@DailyActivity, DailyAverageActivity::class.java)
            intent.putExtra("dateDay", date)
            startActivity(intent)
        }

    }
}