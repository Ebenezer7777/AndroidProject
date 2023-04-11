import com.example.deezers.service.AlbumResponse
import com.example.deezers.service.ArtistResponse
import com.example.deezers.service.TrackResponse
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class DeezerApi  {

    private val baseUrl = "https://api.deezer.com"
    private val gson = Gson()
    private val client = OkHttpClient()

    suspend fun searchArtist(query: String): ArtistResponse {
        val url = "$baseUrl/search/artist?q=$query"
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).execute().use { response ->
            val body = response.body?.string()
            return gson.fromJson(body, ArtistResponse::class.java)
        }
    }


    suspend fun searchAlbum(query: String): AlbumResponse {
        val url = "$baseUrl/search/album?q=$query"
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).execute().use { response ->
            val body = response.body?.string()
            return gson.fromJson(body, AlbumResponse::class.java)
        }
    }

    suspend fun searchArtistAlbum(artistId: Long): AlbumResponse {
        val url = "$baseUrl/artist/$artistId/albums"
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).execute().use { response ->
            val body = response.body?.string()
            return gson.fromJson(body, AlbumResponse::class.java)
        }
    }

    suspend fun searchTracks(albumId: Long): TrackResponse {
        val url = "$baseUrl/album/$albumId"
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).execute().use { response ->
            val body = response.body?.string()
            return gson.fromJson(body, TrackResponse::class.java)
        }
    }


}


