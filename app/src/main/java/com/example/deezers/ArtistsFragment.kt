package com.example.deezers

import DeezerService
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deezers.service.CallBackDeezer
import com.example.deezers.service.data.ArtistSearchResult
import com.example.deezers.ui.RecyclerAdapterArtist
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Response
import java.io.IOException

class ArtistsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var artistsAdapter: RecyclerAdapterArtist
    private val deezerService = DeezerService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        recyclerView = view.findViewById(R.id.artists_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val artistQuery = arguments?.getString("artist") ?: ""

        deezerService.searchArtist(artistQuery, object : CallBackDeezer {
            override fun onSuccess(call: Call, response: Response) {
                val gson = Gson()
                val jsonResponse = response.body?.string()
                val artistSearchResult = gson.fromJson(jsonResponse, ArtistSearchResult::class.java)
                val artistsList = artistSearchResult.data

                activity?.runOnUiThread {
                    artistsAdapter = RecyclerAdapterArtist(artistsList) { artistId
                        ->
                        val bundle = Bundle()
                        bundle.putLong("artistId", artistId)
                        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
                    }
                    recyclerView.adapter = artistsAdapter
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
        })
    }
}


/*
class ArtistsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var artistsAdapter: RecyclerAdapterArtist
    private val deezerService = DeezerService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        recyclerView = view.findViewById(R.id.artists_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        // Initialize the adapter with an empty list
        artistsAdapter = RecyclerAdapterArtist(listOf()) { artistId ->
            val bundle = Bundle()
            bundle.putLong("artistId", artistId)
            val albumsFragment = com.example.deezers.AlbumsFragment()
            albumsFragment.arguments = bundle
            parentFragmentManager.beginTransaction()
                .replace(R.id.SecondFragment, albumsFragment)
                .addToBackStack(null)
                .commit()

        }


        recyclerView.adapter = artistsAdapter // Set the adapter to the RecyclerView
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val artistQuery = arguments?.getString("artist") ?: ""

        deezerService.searchArtist(artistQuery, object : CallBackDeezer {
            override fun onSuccess(call: Call, response: Response) {
                val gson = Gson()
                val jsonResponse = response.body?.string()
                val artistSearchResult = gson.fromJson(jsonResponse, ArtistSearchResult::class.java)
                val artistsList = artistSearchResult.data

                activity?.runOnUiThread {
                    // Pass an onClickListener to the RecyclerAdapterArtist to navigate to com.example.deezers.AlbumsFragment
                    artistsAdapter = RecyclerAdapterArtist(artistsList) { artistId ->
                        val bundle = Bundle()
                        bundle.putLong("artistId", artistId)
                        val albumsFragment = com.example.deezers.AlbumsFragment()
                        albumsFragment.arguments = bundle
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.SecondFragment, albumsFragment)
                            .addToBackStack(null)
                            .commit()
                    }
                    recyclerView.adapter = artistsAdapter
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val artistQuery = arguments?.getString("artist") ?: ""

        deezerService.searchArtist(artistQuery, object : CallBackDeezer {
            override fun onSuccess(call: Call, response: Response) {
                val gson = Gson()
                val jsonResponse = response.body?.string()
                val artistSearchResult = gson.fromJson(jsonResponse, ArtistSearchResult::class.java)
                val artistsList = artistSearchResult.data

                activity?.runOnUiThread {
                    artistsAdapter = RecyclerAdapterArtist(artistsList)
                    recyclerView.adapter = artistsAdapter

                }
            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
        })
    }
}
*/