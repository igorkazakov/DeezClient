package com.example.igor.deezclient.viewModels

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.igor.deezclient.application.DeezApplication
import com.example.igor.deezclient.data.RepositoryInterface
import com.example.igor.deezclient.data.common.ErrorModel
import com.example.igor.deezclient.data.server.model.TrackModel
import com.example.igor.deezclient.utils.SingleLiveEvent
import com.igorkazakov.user.foursquareclient.data.server.Repository
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindToLifecycle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PlaylistViewModel(application: DeezApplication,
                        private var mRepository: RepositoryInterface) :
        BaseViewModel<ErrorModel>(application) {

    var playlistLiveData: MutableLiveData<List<TrackModel>> = MutableLiveData()

    fun needLoadAdditionalData(visibleItemCount: Int,
                               totalItemCount: Int,
                               firstVisibleItemPosition: Int): Boolean {
        
        return visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= Repository.PAGE_SIZE
    }

    fun loadPlaylist(playlistId: Int, offset: Int, lifecycleOwner: LifecycleOwner) {

        mRepository.loadTracks(playlistId.toString(), offset)
                .bindToLifecycle(lifecycleOwner)
                .flatMap { Observable.fromIterable(it) }
                .map { TrackModel(it) }
                .toList()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    if (it.isNotEmpty()) playlistLiveData.value = it

                }, {
                    errorsLiveData.value = ErrorModel(it.message, it.message)
                })
    }
}