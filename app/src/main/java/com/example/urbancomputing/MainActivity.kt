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
        var time = "00:00:00"
        var data: Map<String, String>
        ref.child("hourly-weather/$date/$time").get().addOnSuccessListener { it ->
            data = it.children.associate { it.key.toString() to it.value.toString() }

            Log.i("test", "Got value ${data}")
//            var data = it.children.map{it.key to it.value}.toMap()
//            Log.i("test", "Got value ${data[0]}")
        }
        //        data = data.child(id.toString())

//        Log.d("test", "data = ${data.toString()}")
    }
}