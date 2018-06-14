package com.example.shuja1497.musically

import android.os.Handler
import android.os.Message
import android.util.Log

class DownloadHandler : Handler() {

    private val TAG = DownloadHandler::class.java.simpleName

    lateinit var service: DownloadService

    override fun handleMessage(msg: Message?) {

        downloadSong(msg!!.obj.toString())
        service.stopSelf(msg.arg1)

    }

    private fun downloadSong(song: String) {
        val endTime  = System.currentTimeMillis() + 10*1000

        while (System.currentTimeMillis() < endTime){
            Thread.sleep(1000)
        }
        Log.d(TAG, "$song downloaded")
    }
}