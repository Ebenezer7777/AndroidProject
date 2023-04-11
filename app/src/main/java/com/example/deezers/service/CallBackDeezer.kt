package com.example.deezers.service

import com.example.deezers.service.data.AlbumData
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import com.example.deezers.service.data.ArtistData


    interface CallBackDeezer {
        fun onSuccess(call: Call, response: Response)
        fun onFailure(call: Call, e: IOException)
    }



