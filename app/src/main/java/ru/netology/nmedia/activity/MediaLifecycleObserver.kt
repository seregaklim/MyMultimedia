package ru.netology.nmedia.activity

import android.media.MediaPlayer

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner



class MediaLifecycleObserver : LifecycleEventObserver {


    var player: MediaPlayer? = MediaPlayer()


    fun play() {
        player?.setOnPreparedListener {
            it.start()
        }
        player?.prepareAsync()

    }


    fun onPause(){
        player?.setOnPreparedListener {
            it.pause()

        }

        player?.pause()

    }


    fun onStop(){
        player?.stop()
        player=null
    }



    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_PAUSE -> player?.pause()
            Lifecycle.Event.ON_STOP -> { player?.release()
                player = null
            }
            Lifecycle.Event.ON_DESTROY -> source.lifecycle.removeObserver(this)
            else -> Unit
        }
    }

}



