package com.example.shuja1497.musically

import android.app.IntentService
import android.content.Intent
import android.util.Log

class DownloadIntentService : IntentService("DownloadIntentService") {

    private val TAG = DownloadIntentService::class.java.simpleName

    init {
        // by default an intent service will use start sticky . but here START_REDELIVER_INTENT is needed
        // this method changes onStartCommand's return value.
        super.setIntentRedelivery(true)
    }

    override fun onHandleIntent(intent: Intent?) {

        val song = intent!!.extras.getString(MainActivity().SONG_KEY)
        downloadSong(song)
    }

    private fun downloadSong(song: String) {
        val endTime  = System.currentTimeMillis() + 10*1000

        while (System.currentTimeMillis() < endTime){
            Thread.sleep(1000)
        }
        Log.d(TAG, "$song downloaded")
    }

}