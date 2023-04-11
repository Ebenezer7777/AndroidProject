package com.example.deezers.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deezers.R
import com.example.deezers.service.data.TrackData

class RecyclerAdapterTrack(private val trackList: List<TrackData>,
                           private val onTrackClickListener: (TrackData) -> Unit
                           ) : RecyclerView.Adapter<RecyclerAdapterTrack.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_tracks, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(trackList[position], onTrackClickListener)
    }

    override fun getItemCount() = trackList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val trackTitle: TextView = itemView.findViewById(R.id.track_title)
        private val trackImage: ImageView = itemView.findViewById(R.id.track_image)


        fun bind(trackData: TrackData, onTrackClickListener: (TrackData) -> Unit) {
            trackTitle.text = trackData.title

            // Load the track image using Glide or another image loading library
             Glide.with(itemView.context)
                 .load(trackData.cover)
                 .into(trackImage)

            itemView.setOnClickListener {
                onTrackClickListener(trackData)
            }

        }
    }
}
