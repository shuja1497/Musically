package com.example.shuja1497.musically

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.MediaStore

class PlayerService : Service() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate() {
        mediaPlayer = MediaPlayer.create(this, R.raw.jingle)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}