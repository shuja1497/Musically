package com.example.shuja1497.musically

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log

class PlayerService : Service() {

    private var mediaPlayer: MediaPlayer? = null
    val TAG = PlayerService::class.java.simpleName
    val messenger: Messenger = Messenger(PlayerHandler(this))

    override fun onCreate() {
        Log.d(TAG, "onCreate")
        mediaPlayer = MediaPlayer.create(this, R.raw.jingle)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        // making notification
        val notificationBuilder = Notification.Builder(this)
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
        val notification = notificationBuilder.build()
        startForeground(1, notification) // 0 id is not allowed

        mediaPlayer!!.setOnCompletionListener {
            stopSelf() // stops service immediately
            stopForeground(true)
        }
        return Service.START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind")
        return messenger.binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        mediaPlayer!!.release()
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