package com.example.igor.deezclient.data.server.model



import com.example.igor.deezclient.data.server.response.tracks.Track
import java.text.SimpleDateFormat


class TrackModel(private var track: Track) {

    fun getDuration() : String {
        val df = SimpleDateFormat("mm:ss")
        val duration = track.duration ?: 1
        return df.format(duration * 1000)
    }

    fun getTitle() : String {
        return track.title ?: ""
    }

    fun getArtist() : String {
        return track.artist?.name ?: ""
    }
}