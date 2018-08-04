package com.igorkazakov.user.foursquareclient.data.server

import com.example.igor.deezclient.data.server.response.tracks.TracksResponse
import com.igorkazakov.user.foursquareclient.data.server.response.recommendations.PlaylistsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DeezApi {

    @GET("user/{client_id}/playlists?")
    fun playlists(@Path("client_id") clientId: String): Observable<PlaylistsResponse>

//    @GET("playlist/{playlist_id}?")
//    fun playlist(@Path("playlist_id") playlistId: String): Observable<PlaylistsResponse>

    @GET("playlist/{playlist_id}/tracks?")
    fun tracks(@Path("playlist_id") playlistId: String,
               @Query("limit") limit: Int,
               @Query("index") index: Int): Observable<TracksResponse>
}