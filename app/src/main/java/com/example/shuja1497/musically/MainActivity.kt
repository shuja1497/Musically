package com.example.shuja1497.musically

import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

var playerService: PlayerService = PlayerService()
var mBound = false

class MainActivity : AppCompatActivity()  {

    val  SONG_KEY = "song"
    private  var serviceConnection: ServiceConnection = MyServiceConnection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_download.setOnClickListener {
            Toast.makeText(this, "Downloading", Toast.LENGTH_SHORT).show()
            // send msgs  to handler for processing
            Playlist().playlist.forEach {
//                val intent = Intent(this, DownloadService::class.java )
                val intent = Intent(this, DownloadIntentService::class.java )
                intent.putExtra(SONG_KEY, it)
                startService(intent)
            }
        }

        buttonPlay.setOnClickListener {

            if (mBound){
                if(playerService.isPlaying()){
                    playerService.pause()
                    buttonPlay.text = "Play"
                }else{
                    playerService.play()
                    buttonPlay.text = "Pause"
                }
            }
        }
    }



    override fun onStart() {
        super.onStart()
        bindService(Intent(this,PlayerService::class.java), serviceConnection,
                Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if (mBound) {
            unbindService(serviceConnection)
            mBound = false
        }
    }
}
