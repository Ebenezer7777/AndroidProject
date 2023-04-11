package com.example.deezers.service

import DeezerApi

class DeezerApiClient(private val deezerApi: DeezerApi) {

    suspend fun searchArtist(query: String): ArtistResponse {
        return deezerApi.searchArtist(query)
    }

    suspend fun searchAlbum(query: String): AlbumResponse {
        return deezerApi.searchAlbum(query)
    }

    suspend fun searchArtistAlbum(artistId: Long): AlbumResponse {
        return deezerApi.searchArtistAlbum(artistId)
    }

    suspend fun searchTracks(albumId: Long): TrackResponse {
        return deezerApi.searchTracks(albumId)
    }
}


