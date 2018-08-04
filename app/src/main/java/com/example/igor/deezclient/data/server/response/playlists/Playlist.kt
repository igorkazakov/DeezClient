package com.example.igor.deezclient.data.server.response.playlists

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Playlist {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("picture")
    @Expose
    var picture: String? = null
    @SerializedName("picture_medium")
    @Expose
    var pictureMedium: String? = null
    @SerializedName("picture_big")
    @Expose
    var pictureBig: String? = null
}