package com.example.shuja1497.musically

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val  SONG_KEY = "song"
    var mBound = false
    val TAG = MainActivity::class.java.simpleName
    private lateinit var buttonPlay: Button
    private lateinit var serviceMessenger: Messenger
    private var activityMessenger: Messenger = Messenger(ActivityHandler(this))

    private var serviceConnection = object : ServiceConnection{

        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            Log.d(TAG, "onServiceConnected")
            mBound = true
            serviceMessenger = Messenger(binder)
            val message = Message.obtain()
            message.arg1 = 2
            message.arg2 = 1
            // to hear back we need to provide our own messenger as a replyTo parameter
            message.replyTo = activityMessenger
            serviceMessenger.send(message)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
            Log.d(TAG, "onServiceDisconnected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "", Toast.LENGTH_SHORT).show()

        buttonPlay = findViewById(R.id.buttonPlay)

        button_download.setOnClickListener {
            Toast.makeText(this, "Downloading", Toast.LENGTH_SHORT).show()
            // send msgs  to handler for processing
            Playlist().playlist.forEach {
                //                val intent = Intent(this, DownloadService::class.java )
                val intent = Intent(this, DownloadIntentService::class.java)
                intent.putExtra(SONG_KEY, it)
                startService(intent)
            }
        }

        buttonPlay.setOnClickListener {
            if (mBound) {
                startService(Intent(this, PlayerService::class.java))
                val message = Message.obtain()
                message.arg1 = 2
                // to hear back we need to provide our own messenger as a replyTo parameter
                message.replyTo = activityMessenger
                serviceMessenger.send(message)
            }
        }
    }

    fun changePlayButtonText(text: String){
        buttonPlay.text = text
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
