package com.igorkazakov.user.foursquareclient.data.server.response.recommendations

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.example.igor.deezclient.data.server.response.playlists.Playlist



class PlaylistsResponse {

    @SerializedName("data")
    @Expose
    var data: List<Playlist>? = null
    @SerializedName("total")
    @Expose
    var total: Int? = null
    @SerializedName("next")
    @Expose
    var next: String? = null
}