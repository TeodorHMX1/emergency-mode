package com.zeoflow.emergency.utils

import android.media.AudioManager

object Utils {
    /**
     * The audio stream for phone calls
     */
    const val STREAM_VOICE_CALL = AudioManager.STREAM_VOICE_CALL

    /**
     * The audio stream for system sounds
     */
    const val STREAM_SYSTEM = AudioManager.STREAM_SYSTEM

    /**
     * The audio stream for the phone ring
     */
    const val STREAM_RING = AudioManager.STREAM_RING

    /**
     * The audio stream for music playback
     */
    const val STREAM_MUSIC = AudioManager.STREAM_MUSIC

    /**
     * The audio stream for alarms
     */
    const val STREAM_ALARM = AudioManager.STREAM_ALARM

    /**
     * The audio stream for notifications
     */
    const val STREAM_NOTIFICATION = AudioManager.STREAM_NOTIFICATION

    /**
     * The audio stream for DTMF Tones
     */
    const val STREAM_DTMF = AudioManager.STREAM_DTMF
}