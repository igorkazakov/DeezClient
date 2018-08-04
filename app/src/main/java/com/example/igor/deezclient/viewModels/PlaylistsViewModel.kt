package com.example.igor.deezclient.viewModels

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import com.example.igor.deezclient.application.DeezApplication
import com.example.igor.deezclient.data.RepositoryInterface
import com.example.igor.deezclient.data.common.ErrorModel
import com.example.igor.deezclient.data.server.model.PlaylistModel
import com.example.igor.deezclient.utils.SingleLiveEvent
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindToLifecycle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PlaylistsViewModel(application: DeezApplication,
                         private var mRepository: RepositoryInterface) :
        BaseViewModel<ErrorModel>(application) {

    var playlistsLiveData: MutableLiveData<List<PlaylistModel>> = MutableLiveData()

    fun needData(): Boolean {
        return playlistsLiveData.value == null
    }

    fun loadPlaylists(lifecycleOwner: LifecycleOwner) {

        mRepository.loadPlaylists()
                .bindToLifecycle(lifecycleOwner)
                .flatMap { Observable.fromIterable(it) }
                .map { PlaylistModel(it) }
                .toList()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    playlistsLiveData.value = it
                }, {
                    errorsLiveData.value = ErrorModel(it.message, it.message)
                })
    }
}