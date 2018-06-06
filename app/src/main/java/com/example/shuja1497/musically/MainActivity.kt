package com.example.shuja1497.musically

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View){

        when(view.id){

            R.id.button_download->{
                Toast.makeText(this, "Downloading", Toast.LENGTH_SHORT).show()

                thread(true,
                        name = "DownloadThread",
                        block = {
                            downloadSong()
                        })
            }
        }
    }

    private fun downloadSong() {
        val endTime  = System.currentTimeMillis() + 10*1000

        while (System.currentTimeMillis() < endTime){
            Thread.sleep(1000)
        }
        Log.d(TAG, "Song downloaded")
    }
}
