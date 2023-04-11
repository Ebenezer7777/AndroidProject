package com.example.deezers
import DeezerService
import RecyclerAdapterAlbum
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deezers.service.CallBackDeezer
import com.example.deezers.service.data.AlbumSearchResult
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Response
import java.io.IOException


class AlbumsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var albumsAdapter: RecyclerAdapterAlbum
    private val deezerService = DeezerService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        recyclerView = view.findViewById(R.id.albums_recycler_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val artistId = arguments?.getLong("artistId") ?: return

        deezerService.searchArtistAlbum(artistId, object : CallBackDeezer {
            override fun onSuccess(call: Call, response: Response) {
                val gson = Gson()
                val albumData = gson.fromJson(response.body?.string(), AlbumSearchResult ::class.java)
                val albumsList = albumData.data

                activity?.runOnUiThread {
                    albumsAdapter = RecyclerAdapterAlbum(albumsList) { albumId ->
                        val bundle = Bundle()
                        bundle.putLong("albumId", albumId)
                        findNavController().navigate(R.id.action_AlbumsFragment_to_TracksFragment, bundle)
                    }

                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = albumsAdapter
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
        })
    }

}

/*
class com.example.deezers.AlbumsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var albumsAdapter: RecyclerAdapterAlbum

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        recyclerView = view.findViewById(R.id.albums_recycler_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Replace this list with the actual list of albums fetched from the API
        val albumsList = listOf<AlbumData>()

        albumsAdapter = RecyclerAdapterAlbum(albumsList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = albumsAdapter
    }
}
*/