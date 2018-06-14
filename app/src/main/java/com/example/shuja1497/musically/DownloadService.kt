package com.example.shuja1497.musically

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Message
import android.util.Log

class DownloadService: Service() {

    private val TAG = DownloadService::class.java.simpleName

    private  var downloadHandler: DownloadHandler? = null

    override fun onCreate() {
        val thread = DownloadThread()
        thread.name = "DownloadThread"
        thread.start()

        while (thread.downloadHandler==null){
        }
        downloadHandler = thread.downloadHandler
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val song  = intent!!.extras!!.getString(MainActivity().SONG_KEY)

        val message = Message.obtain()
        message.obj = song
        downloadHandler!!.sendMessage(message)

        return Service.START_REDELIVER_INTENT
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}