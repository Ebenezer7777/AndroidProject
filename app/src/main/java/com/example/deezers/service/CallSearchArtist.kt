package com.example.deezers.service

import DeezerService


class CallSearchArtist(private val deezerService: DeezerService) {

    fun searchArtist(query: String, callback: CallBackDeezer) {
        deezerService.searchArtist(query, callback)
    }
}

