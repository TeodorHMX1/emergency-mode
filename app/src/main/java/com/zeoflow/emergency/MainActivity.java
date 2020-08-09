package com.zeoflow.emergency;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import static com.zeoflow.emergency.utils.Utils.STREAM_MUSIC;
import static com.zeoflow.emergency.utils.Utils.STREAM_NOTIFICATION;

public class MainActivity extends AppCompatActivity
{

    int sound;
    private SoundPool soundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soundPool = new SoundPool.Builder()
                .setMaxStreams(1)
                .build();

        sound = soundPool.load(this, R.raw.alarm, 1);

        soundPool.setOnLoadCompleteListener((soundPool, soundID, i1) ->
        {
            if (sound == soundID)
            {
                AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                audioManager.setStreamVolume(STREAM_MUSIC, audioManager.getStreamMaxVolume(STREAM_MUSIC), 0);
                soundPool.play(sound, 1f, 1f, 0, 999, 1F);
            }
        });

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        soundPool.release();
    }
}