package com.zeoflow.emergency;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import static com.zeoflow.emergency.utils.Utils.STREAM_SYSTEM;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(STREAM_SYSTEM, audioManager.getStreamMaxVolume(STREAM_SYSTEM), 0);

    }
}