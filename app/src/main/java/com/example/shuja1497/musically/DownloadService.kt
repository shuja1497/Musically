package com.example.shuja1497.musically

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class DownloadService: Service() {

    private val TAG = DownloadService::class.java.simpleName


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        val song  = intent!!.extras!!.getString(MainActivity().SONG_KEY)
        downloadSong(song)
        return Service.START_REDELIVER_INTENT
    }

    private fun downloadSong(song: String) {
        val endTime  = System.currentTimeMillis() + 10*1000

        while (System.currentTimeMillis() < endTime){
            Thread.sleep(1000)
        }
        Log.d(TAG, "$song downloaded")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}