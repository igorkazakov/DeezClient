package com.example.igor.deezclient.viewModels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.location.LocationManager
import android.support.annotation.NonNull
import com.example.igor.deezclient.application.DeezApplication
import com.example.igor.deezclient.data.RepositoryInterface

import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    @Inject
    lateinit var mApplication: DeezApplication

    @Inject
    lateinit var mRepository: RepositoryInterface

    init {
        DeezApplication.appComponent.inject(this)
    }

    @NonNull
    override fun <T : ViewModel> create(@NonNull modelClass: Class<T>): T {
        return when (modelClass) {

            PlaylistsViewModel::class.java -> PlaylistsViewModel(
                    mApplication,
                    mRepository) as T

            PlaylistViewModel::class.java -> PlaylistViewModel(
                    mApplication,
                    mRepository) as T

            else -> {
                ViewModelFactory() as T
            }
        }
    }
}