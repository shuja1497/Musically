package com.example.shuja1497.musically

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val thread = DownloadThread()
        thread.name = "DownloadThread"
        thread.start()

        button_download.setOnClickListener {
            Toast.makeText(this, "Downloading", Toast.LENGTH_SHORT).show()
            // send msgs  to handler for processing
            Playlist().playlist.forEach {
                val message = Message.obtain()
                message.obj = it

                // send msg to the handler so it can be added to the msg queue
                thread.downloadHandler.sendMessage(message)
                // after this u need to twll the handler what it shud do on getting the msg
            }
        }
    }
}
