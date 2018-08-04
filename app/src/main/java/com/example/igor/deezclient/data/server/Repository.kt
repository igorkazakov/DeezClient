package com.igorkazakov.user.foursquareclient.data.server

import com.example.igor.deezclient.data.RepositoryInterface
import com.example.igor.deezclient.data.common.ErrorModel
import com.example.igor.deezclient.data.server.response.playlists.Playlist
import com.example.igor.deezclient.data.server.response.tracks.Track
import com.example.igor.deezclient.utils.SingleLiveEvent
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Repository private constructor() : RepositoryInterface {

    private object HOLDER {
        val INSTANCE = Repository()
    }

    companion object {
        val instance: Repository by lazy { HOLDER.INSTANCE }
        const val PAGE_SIZE = 20
    }

    private val baseUrl = "https://api.deezer.com/"
    private val clientId = "5"

    private val service: DeezApi by lazy {
        Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()
                .create(DeezApi::class.java)
    }

    override fun loadPlaylists() : Observable<List<Playlist>> {

        return service.playlists(clientId)
                .subscribeOn(Schedulers.io())
                .flatMap {

                    Observable.fromArray(it.data)
                }
    }

    override fun loadTracks(playlistId: String, offset: Int) : Observable<List<Track>> {

        return service.tracks(playlistId, PAGE_SIZE, offset)
                .subscribeOn(Schedulers.io())
                .flatMap {

                    Observable.fromArray(it.data)
                }
    }
}