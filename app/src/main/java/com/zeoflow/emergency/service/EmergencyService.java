package com.zeoflow.emergency.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import com.zeoflow.emergency.R;

import static com.zeoflow.emergency.utils.Utils.STREAM_MUSIC;

public class EmergencyService extends Service
{

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    int sound;
    private SoundPool soundPool;
    float volume = 0.01f;
    private Runnable runnable;
    private int alarmMode = 1;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        if (intent.getExtras() != null)
        {
            alarmMode = intent.getExtras().getInt("alarm");
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate()
    {

        soundPool = new SoundPool.Builder()
                .setMaxStreams(1)
                .build();

        int alarmSound = R.raw.alarm_1;
        if (alarmMode == 2)
        {
            alarmSound = R.raw.alarm_2;
        } else if (alarmMode == 3)
        {
            alarmSound = R.raw.alarm_3;
        }
        String path = "android.resource://" + getPackageName() + "/" + alarmSound;
        MediaPlayer mp = MediaPlayer.create(this, Uri.parse(path));
        long duration = mp.getDuration() / 100 + 1000;

        sound = soundPool.load(this, alarmSound, 1);

        soundPool.setOnLoadCompleteListener((soundPool, soundID, i1) ->
        {
            if (sound == soundID)
            {
                Handler mHandler = new Handler(Looper.getMainLooper());

                runnable = () ->
                {

                    AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                    audioManager.setStreamVolume(STREAM_MUSIC, audioManager.getStreamMaxVolume(STREAM_MUSIC), 0);
                    soundPool.play(sound, volume, volume, 0, 0, 1F);
                    if (volume < 1f)
                    {
                        volume += 0.005f;
                    }
                    mHandler.postDelayed(runnable, duration);
                };
                mHandler.postDelayed(runnable, duration);
            }
        });
    }

    @Override
    public void onDestroy()
    {
        soundPool.release();
    }

}