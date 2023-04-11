import com.example.deezers.service.CallBackDeezer
import okhttp3.*
import java.io.IOException

class DeezerService {
    fun searchArtist(artistQuery: String, callback: CallBackDeezer) {
        val url = "https://api.deezer.com/search/artist?q=$artistQuery"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailure(call, e)
            }

            override fun onResponse(call: Call, response: Response) {
                callback.onSuccess(call, response)
            }
        })
    }



    fun searchAlbum(albumQuery: String, callback: CallBackDeezer) {
        val url = "https://api.deezer.com/search/album?q=$albumQuery"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailure(call, e)
            }

            override fun onResponse(call: Call, response: Response) {
                callback.onSuccess(call, response)
            }
        })


    }

    fun searchArtistAlbum(artistId: Long, callback: CallBackDeezer) {
        val url = "https://api.deezer.com/artist/$artistId/albums"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailure(call, e)
            }

            override fun onResponse(call: Call, response: Response) {
                callback.onSuccess(call, response)
            }
        })
    }

    fun searchAlbumTracks(albumId: Long, callback: CallBackDeezer) {
        val url = "https://api.deezer.com/album/$albumId/tracks"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailure(call, e)
            }

            override fun onResponse(call: Call, response: Response) {
                callback.onSuccess(call, response)
            }
        })
    }

}