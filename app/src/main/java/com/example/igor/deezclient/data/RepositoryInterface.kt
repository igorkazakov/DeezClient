package com.example.igor.deezclient.data

import com.example.igor.deezclient.data.server.response.playlists.Playlist
import com.example.igor.deezclient.data.server.response.tracks.Track
import com.example.igor.deezclient.data.server.response.tracks.TracksResponse
import io.reactivex.Observable

interface RepositoryInterface {

    fun loadPlaylists() : Observable<List<Playlist>>
    fun loadTracks(playlistId: String, offset: Int) : Observable<TracksResponse>
}