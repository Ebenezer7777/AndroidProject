package com.example.deezers.service

import DeezerService


class CallSearchArtistAlbum(private val deezerService: DeezerService) {

    fun searchArtistAlbum(artistId: Long, callback: CallBackDeezer) {
        deezerService.searchArtistAlbum(artistId, callback)
    }
}
