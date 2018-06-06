package com.example.shuja1497.musically

import android.util.Log

class DownloadThread : Thread() {

    private val TAG = DownloadThread::class.java.simpleName

    override fun run() {
        Playlist().playlist.forEach {
            downloadSong()
        }
    }

    private fun downloadSong() {
        val endTime  = System.currentTimeMillis() + 10*1000

        while (System.currentTimeMillis() < endTime){
            Thread.sleep(1000)
        }
        Log.d(TAG, "Song downloaded")
    }
}