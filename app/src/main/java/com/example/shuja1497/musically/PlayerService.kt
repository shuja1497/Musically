package com.example.shuja1497.musically

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.MediaStore
import android.util.Log

class PlayerService : Service() {

    private lateinit var mediaPlayer: MediaPlayer
    val TAG = PlayerService::class.java.simpleName

    override fun onCreate() {
        Log.d(TAG, "onCreate")
        mediaPlayer = MediaPlayer.create(this, R.raw.jingle)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind")
        return null
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        mediaPlayer.release()
    }

    // client methods

    fun play(){
        mediaPlayer.start()
    }

    fun pause(){
        mediaPlayer.pause()
    }
}