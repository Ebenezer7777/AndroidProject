package com.example.deezers

import DeezerService
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deezers.service.CallBackDeezer
import com.example.deezers.service.data.TrackData
import com.example.deezers.service.data.TrackSearchResult
import com.example.deezers.ui.RecyclerAdapterTrack
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Response
import java.io.IOException


class TracksFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tracksAdapter: RecyclerAdapterTrack
    private val deezerService = DeezerService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tracks, container, false)
        recyclerView = view.findViewById(R.id.tracks_recycler_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val albumId = arguments?.getLong("albumId") ?: return

        // Call the API to get the tracks for the specified album ID
        deezerService.searchAlbumTracks(albumId, object : CallBackDeezer {
            override fun onSuccess(call: Call, response: Response) {
                val gson = Gson()
                val jsonResponse = response.body?.string()
                val trackSearchResult = gson.fromJson(jsonResponse, TrackSearchResult::class.java)
                val tracksList = trackSearchResult.data

                activity?.runOnUiThread {
                    tracksAdapter = RecyclerAdapterTrack(tracksList) { trackdata ->
                            val bundle = Bundle()
                            bundle.putLong("trackId", trackdata.id)
                            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)

                    }
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = tracksAdapter
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
        })
    }
}
