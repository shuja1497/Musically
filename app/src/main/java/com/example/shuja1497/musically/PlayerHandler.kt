package com.example.shuja1497.musically

import android.os.Handler
import android.os.Message


class PlayerHandler(private var playerService: PlayerService): Handler() {

    override fun handleMessage(msg: Message?) {
        when(msg!!.arg1){

            0->{// play
                playerService.play()
            }

            1->{//pause
                playerService.pause()
            }

            2->{// is Playing
                val isPlaying  = if (playerService.isPlaying()) 1 else 0
                val message = Message.obtain()
                message.arg1 = isPlaying
                if (msg.arg2==1){
                    message.arg2=1
                }
                message.replyTo = playerService.messenger
                msg.replyTo.send(message) // sending this to the activity
            }
        }
    }
}