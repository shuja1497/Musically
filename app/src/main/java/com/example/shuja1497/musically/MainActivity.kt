package com.example.shuja1497.musically

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val  SONG_KEY = "song"
    var playerService: PlayerService = PlayerService()
    var mBound = false
    val TAG = MainActivity::class.java.simpleName
    private lateinit var buttonPlay: Button

    private var serviceConnection = object : ServiceConnection{

        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            Log.d(TAG, "onServiceConnected")
            mBound = true
            val localBinder = binder as PlayerService.LocalBinder
            playerService = localBinder.getService()

            if (playerService.isPlaying()) {
                buttonPlay.text = "Pause"
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
            Log.d(TAG, "onServiceDisconnected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonPlay = findViewById(R.id.buttonPlay)

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
                    startService(Intent(this,PlayerService::class.java))
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
