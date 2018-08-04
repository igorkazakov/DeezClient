package com.example.igor.deezclient.data.server.response.tracks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class TracksResponse {

    @SerializedName("data")
    @Expose
    var data: List<Track>? = null
    @SerializedName("total")
    @Expose
    var total: Int? = null
    @SerializedName("next")
    @Expose
    var next: String? = null
}