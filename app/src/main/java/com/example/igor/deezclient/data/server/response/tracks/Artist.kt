package com.example.igor.deezclient.data.server.response.tracks

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Artist {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
}