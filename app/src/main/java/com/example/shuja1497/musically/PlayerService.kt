package com.example.shuja1497.musically

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log

class PlayerService : Service() {

    private var mediaPlayer: MediaPlayer? = null
    val TAG = PlayerService::class.java.simpleName
    private var mBinder: IBinder = LocalBinder()

    override fun onCreate() {
        Log.d(TAG, "onCreate")
        mediaPlayer = MediaPlayer.create(this, R.raw.jingle)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer!!.setOnCompletionListener {
            stopSelf() // stops service immediately
        }
        return Service.START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind")
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        mediaPlayer!!.release()
    }

    // making a class extending Binder to connect the service with the activity.

    inner class LocalBinder: Binder(){

        fun getService(): PlayerService{
            return this@PlayerService
        }
    }


    // client methods

    fun play(){
        mediaPlayer!!.start()
    }

    fun pause(){
        mediaPlayer!!.pause()
    }

    fun isPlaying() : Boolean{
        return mediaPlayer!!.isPlaying
    }
}