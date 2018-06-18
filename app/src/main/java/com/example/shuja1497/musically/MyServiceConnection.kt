package com.example.shuja1497.musically

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import kotlinx.android.synthetic.main.activity_main.*

class MyServiceConnection: ServiceConnection {


    override fun onServiceDisconnected(name: ComponentName?) {
       mBound = false
    }

    override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
        mBound = true
        val localBinder = binder as PlayerService.LocalBinder
        playerService = localBinder.getService()
        if (playerService.isPlaying()){
            MainActivity().buttonPlay.text = "Pause"
        }
    }
}