package com.example.deezers

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri

object MediaPlayerSingleton {

    private var mediaPlayer: MediaPlayer? = null

    fun playTrack(context: Context, trackUrl: String) {
        mediaPlayer?.stop()
        mediaPlayer = MediaPlayer.create(context, trackUrl.toUri())
        mediaPlayer?.start()
        mediaPlayer?.pause()
        mediaPlayer?.stop()
        mediaPlayer?.isPlaying ?: false
    }



    fun pause() {

    }

    fun stop() {

    }


    fun setDataSource(url: String) {
        mediaPlayer?.setDataSource(url)
    }

    fun prepare() {
        mediaPlayer?.prepare()
    }



    fun isPlaying(): Boolean {
        return mediaPlayer?.isPlaying ?: false

    }

    fun reset() {
        TODO("Not yet implemented")

    }

    fun start(): Boolean {
        mediaPlayer?.start()
        return mediaPlayer?.isPlaying ?: false

    }
}
