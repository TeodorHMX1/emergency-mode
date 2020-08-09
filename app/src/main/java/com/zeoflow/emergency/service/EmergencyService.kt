package com.zeoflow.emergency.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.net.Uri
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import com.zeoflow.emergency.R
import com.zeoflow.emergency.utils.Utils.STREAM_MUSIC

class EmergencyService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private var sound = 0
    private var soundPool: SoundPool? = null
    private var volume = 0.01f
    private var runnable: Runnable? = null
    private var alarmMode = 1
    private var duration: Long = 0
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if (intent.extras != null) {
            alarmMode = intent.extras!!.getInt("alarm")
        }
        var alarmSound = R.raw.alarm_1
        if (alarmMode == 2) {
            alarmSound = R.raw.alarm_2
        } else if (alarmMode == 3) {
            alarmSound = R.raw.alarm_3
        }
        val path = "android.resource://$packageName/$alarmSound"
        val mp = MediaPlayer.create(this, Uri.parse(path))
        duration = mp.duration / 100 * 2 + 1000.toLong()
        if (alarmMode == 2) {
            alarmSound = R.raw.alarm_2
        } else if (alarmMode == 3) {
            alarmSound = R.raw.alarm_3
        }
        sound = soundPool!!.load(this, alarmSound, 1)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        soundPool = SoundPool.Builder()
                .setMaxStreams(1)
                .build()

        soundPool?.setOnLoadCompleteListener { soundPool: SoundPool, soundID: Int, i1: Int ->
            if (sound == soundID) {
                val mHandler = Handler(Looper.getMainLooper())
                runnable = Runnable {
                    val audioManager = applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                    audioManager.setStreamVolume(STREAM_MUSIC, audioManager.getStreamMaxVolume(STREAM_MUSIC), 0)
                    soundPool.play(sound, volume, volume, 0, 0, 1f)
                    if (volume < 1f) {
                        volume += 0.005f
                    }
                    mHandler.postDelayed(runnable!!, duration)
                }
                mHandler.postDelayed(runnable!!, 0)
            }
        }
    }

    override fun onDestroy() {
        soundPool!!.release()
    }
}