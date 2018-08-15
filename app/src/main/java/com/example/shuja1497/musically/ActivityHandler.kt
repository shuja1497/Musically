package com.example.shuja1497.musically

import android.os.Handler
import android.os.Message


class ActivityHandler(private val mainActivity: MainActivity): Handler(){

    override fun handleMessage(msg: Message?) {
        if (msg!!.arg1 == 0){
            // music is not playing
            if (msg.arg2==1){
                mainActivity.changePlayButtonText("Play")
            }else {
                val message = Message.obtain()
                message.arg1 = 0
                msg.replyTo.send(message)
                // Play the music and change the play button to say "pause"
                mainActivity.changePlayButtonText("Pause")
            }
        }else if (msg.arg1 == 1){
            // music is playing
            if (msg.arg2==1){
                mainActivity.changePlayButtonText("Pause")
            }else {
                val message = Message.obtain()
                message.arg1 = 1
                msg.replyTo.send(message)
                // Pause the music and change the play button to say "play"
                mainActivity.changePlayButtonText("Play")
            }
        }
    }
}