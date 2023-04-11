package com.example.deezers.service

import DeezerApi
import java.io.IOException
import kotlinx.coroutines.*


class CallSearchAlbum(
    private val query: String,
    private val deezerApi: DeezerApi,
    private val callback: CallbackAlbum
) {

    private var job: Job? = null

    fun execute() {
        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val albumResponse = deezerApi.searchAlbum(query)
                withContext(Dispatchers.Main) {
                    callback.onSuccess(albumResponse)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback.onFailure(IOException("Error executing searchAlbum: ${e.message}"))
                }
            }
        }
    }

    fun cancel() {
        job?.cancel()
    }

    interface CallbackAlbum {
        fun onSuccess(albumResponse: AlbumResponse)
        fun onFailure(e: IOException)
    }
}
