package com.example.deezers.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.deezers.R
import com.example.deezers.service.data.ArtistData


class RecyclerAdapterArtist(private val artistList: List<ArtistData>, private val onClickListener: (Long) -> Unit) :
    RecyclerView.Adapter<RecyclerAdapterArtist.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_first, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(artistList[position], onClickListener)
    }

    override fun getItemCount() = artistList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val artistName: TextView = itemView.findViewById(R.id.artist_name)
        private val artistPicture: ImageView = itemView.findViewById(R.id.artist_cover)

        fun bind(artistData: ArtistData, onClickListener: (Long) -> Unit) {
            artistName.text = artistData.name
            // Load the artist picture image using Glide or another image loading library
            Glide.with(itemView.context)
                .load(artistData.picture)
                .into(artistPicture)

            // Set the onClickListener for the artist name
            artistName.setOnClickListener {
                onClickListener(artistData.id)

            }
        }
    }
}

/*
class RecyclerAdapterArtist(private val artistList: List<ArtistData>) : RecyclerView.Adapter<RecyclerAdapterArtist.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_first, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artistData = artistList[position]

        // Set the artist name
        holder.artistName.text = artistData.name

        // Load the artist picture image using Glide or another image loading library
        Glide.with(holder.itemView.context)
            .load(artistData.picture)
            .into(holder.artistPicture)

        // Set a click listener on the item view
        holder.itemView.setOnClickListener {
            // Create a new instance of com.example.deezers.AlbumsFragment and set the artist id as an argument
            val albumsFragment = com.example.deezers.AlbumsFragment()
            val bundle = Bundle()
            bundle.putLong("artist_id", artistData.id)
            albumsFragment.arguments = bundle

            // Navigate to com.example.deezers.AlbumsFragment
            val activity = holder.itemView.context as MainActivity
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, albumsFragment)
                .addToBackStack(null)
                .commit()

        }
    }


    override fun getItemCount() = artistList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val artistName: TextView = itemView.findViewById(R.id.artist_name)
        val artistPicture: ImageView = itemView.findViewById(R.id.artist_cover)

        fun bind(artistData: ArtistData) {
            artistName.text = artistData.name
            // Load the artist picture image using Glide or another image loading library
        Glide.with(itemView.context)
                 .load(artistData.picture)
                 .into(artistPicture)
        }
    }
}
*/