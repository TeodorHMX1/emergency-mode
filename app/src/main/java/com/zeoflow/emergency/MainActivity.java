package com.zeoflow.emergency;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    /**
     * The audio stream for phone calls
     */
    public static final int STREAM_VOICE_CALL = AudioManager.STREAM_VOICE_CALL;
    /**
     * The audio stream for system sounds
     */
    public static final int STREAM_SYSTEM = AudioManager.STREAM_SYSTEM;
    /**
     * The audio stream for the phone ring
     */
    public static final int STREAM_RING = AudioManager.STREAM_RING;
    /**
     * The audio stream for music playback
     */
    public static final int STREAM_MUSIC = AudioManager.STREAM_MUSIC;
    /**
     * The audio stream for alarms
     */
    public static final int STREAM_ALARM = AudioManager.STREAM_ALARM;
    /**
     * The audio stream for notifications
     */
    public static final int STREAM_NOTIFICATION = AudioManager.STREAM_NOTIFICATION;
    /**
     * The audio stream for DTMF Tones
     */
    public static final int STREAM_DTMF = AudioManager.STREAM_DTMF;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(STREAM_SYSTEM, audioManager.getStreamMaxVolume(STREAM_SYSTEM), 0);

    }
}