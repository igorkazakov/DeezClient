package com.example.igor.deezclient.data.server.response.tracks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Track {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("duration")
    @Expose
    var duration: Int? = null
    @SerializedName("artist")
    @Expose
    var artist: Artist? = null
}