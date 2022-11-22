package com.example.urbancomputing

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ref = FirebaseDatabase.getInstance().reference
        val database = Firebase.database.reference

        var date = "2022-01-01"
        var time = "$date 00:00:00"

        var data = ref.child("hourly-weather/$date/$time")
        var id = data.key
//        data = data.child(id.toString())

        Log.d("test", "data = $strdata")
    }
}