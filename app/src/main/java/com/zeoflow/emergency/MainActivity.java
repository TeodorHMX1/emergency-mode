package com.zeoflow.emergency;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import static com.zeoflow.emergency.utils.Utils.STREAM_MUSIC;

public class MainActivity extends AppCompatActivity
{

    int sound;
    private SoundPool soundPool;
    float volume = 0.01f;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soundPool = new SoundPool.Builder()
                .setMaxStreams(1)
                .build();

        sound = soundPool.load(this, R.raw.emergency, 1);

        soundPool.setOnLoadCompleteListener((soundPool, soundID, i1) ->
        {
            if (sound == soundID)
            {
                Handler mHandler = new Handler(Looper.getMainLooper());

                runnable = () ->
                {

                    AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                    audioManager.setStreamVolume(STREAM_MUSIC, audioManager.getStreamMaxVolume(STREAM_MUSIC), 0);
                    soundPool.play(sound, volume, volume, 0, 1, 1F);
                    if (volume < 1f)
                    {
                        volume += 0.001f;
                    }
                    mHandler.postDelayed(runnable, 1500);
                };
                mHandler.postDelayed(runnable, 1500);
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