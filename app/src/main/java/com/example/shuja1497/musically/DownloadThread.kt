package com.example.shuja1497.musically

import android.os.Looper
import android.util.Log

class DownloadThread : Thread() {

    private val TAG = DownloadThread::class.java.simpleName
    var downloadHandler: DownloadHandler? = null

    override fun run() {

        Looper.prepare() // creates a looper for a thread along witht the msg queue
        downloadHandler = DownloadHandler()
        // by default a handler is associated with the looper for the current thread.
        // since this is inside the run method of download thread so it the current thread

        Looper.loop() // start looping over the msg queue
    }
}