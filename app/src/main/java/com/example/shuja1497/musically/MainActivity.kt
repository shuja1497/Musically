package com.example.shuja1497.musically

import android.content.Intent
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
    val  SONG_KEY = "song"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button_download.setOnClickListener {
            Toast.makeText(this, "Downloading", Toast.LENGTH_SHORT).show()
            // send msgs  to handler for processing
            Playlist().playlist.forEach {
                val intent = Intent(this, DownloadService::class.java )
                intent.putExtra(SONG_KEY, it)
                startService(intent)
            }
        }
    }
}
