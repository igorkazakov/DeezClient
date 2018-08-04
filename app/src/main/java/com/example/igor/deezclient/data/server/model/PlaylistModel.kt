package com.example.igor.deezclient.data.server.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.BindingAdapter
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import com.example.igor.deezclient.data.server.response.playlists.Playlist
import com.facebook.drawee.view.SimpleDraweeView

class PlaylistModel(): Parcelable {

    var title: String? = null
    var image: String? = null
    var imageBig: String? = null
    var playlistId: Int = -1

    constructor(model: Playlist) : this() {

        title = model.title.toString()
        image = model.picture.toString()
        imageBig = model.pictureBig.toString()

        model.id?.let {
            playlistId = it
        }
    }

    constructor(parcel: Parcel) : this() {
        readFromParcel(parcel)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(image)
        parcel.writeString(imageBig)
        parcel.writeInt(playlistId)
    }

    private fun readFromParcel(parcel: Parcel) {
        title = parcel.readString()
        image = parcel.readString()
        imageBig = parcel.readString()
        playlistId = parcel.readInt()
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlaylistModel> {
        override fun createFromParcel(parcel: Parcel): PlaylistModel {
            return PlaylistModel(parcel)
        }

        override fun newArray(size: Int): Array<PlaylistModel?> {
            return arrayOfNulls(size)
        }
    }
}



