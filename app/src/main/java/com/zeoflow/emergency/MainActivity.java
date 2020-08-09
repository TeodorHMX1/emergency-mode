package com.zeoflow.emergency;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.zeoflow.emergency.service.EmergencyService;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent emergencyMode = new Intent(this, EmergencyService.class);
        emergencyMode.putExtra("alarm", 3);
        startService(emergencyMode);

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}