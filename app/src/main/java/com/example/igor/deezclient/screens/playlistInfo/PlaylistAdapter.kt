package com.example.igor.deezclient.screens.playlistInfo

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.igor.deezclient.R
import com.example.igor.deezclient.data.server.model.TrackModel
import com.example.igor.deezclient.databinding.ItemTrackBinding

class PlaylistAdapter : RecyclerView.Adapter<PlaylistAdapter.TrackViewHolder>() {

    private var mPlaylists: MutableList<TrackModel> = mutableListOf()

    fun addItems(items: List<TrackModel>) {
        mPlaylists.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistAdapter.TrackViewHolder {

        val binding: ItemTrackBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_track,
                parent,
                false)

        return TrackViewHolder(binding)
    }

    override fun onBindViewHolder(vholder: TrackViewHolder, position: Int) {
        vholder.binding.track = mPlaylists[position]
        vholder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return mPlaylists.size
    }

    inner class TrackViewHolder(var binding: ItemTrackBinding) : RecyclerView.ViewHolder(binding.root)
}