package com.example.shuja1497.musically

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder

class MyServiceConnection: ServiceConnection {
    override fun onServiceDisconnected(name: ComponentName?) {
        MainActivity().mBound = true
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        MainActivity().mBound = false
    }
}