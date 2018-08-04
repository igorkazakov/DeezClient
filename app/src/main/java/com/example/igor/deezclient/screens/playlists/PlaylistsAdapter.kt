package com.example.igor.deezclient.screens.playlists

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import com.example.igor.deezclient.R
import com.example.igor.deezclient.data.server.model.PlaylistModel

import com.example.igor.deezclient.databinding.ItemPlaylistBinding


class PlaylistsAdapter(var playlists: List<PlaylistModel>,
                       private var mListener: PlaylistsItemListener?) :
        RecyclerView.Adapter<PlaylistsAdapter.PlaylistViewHolder>() {

    inner class PlaylistViewHolder(var binding: ItemPlaylistBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistsAdapter.PlaylistViewHolder {

        val binding: ItemPlaylistBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_playlist,
                parent,
                false)

        binding.itemListener = mListener

        return PlaylistViewHolder(binding)
    }

    override fun onBindViewHolder(vholder: PlaylistViewHolder, position: Int) {
        vholder.binding.playlist = playlists[position]
        vholder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {

        return playlists.size
    }

    interface PlaylistsItemListener {
        fun onItemClick(item: PlaylistModel)
    }
}