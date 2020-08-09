package com.zeoflow.emergency

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zeoflow.emergency.service.EmergencyService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emergencyMode = Intent(this, EmergencyService::class.java)
        emergencyMode.putExtra("alarm", 1)
        startService(emergencyMode)

    }

}