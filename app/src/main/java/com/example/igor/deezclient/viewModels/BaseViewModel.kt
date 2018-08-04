package com.example.igor.deezclient.viewModels

import android.arch.lifecycle.AndroidViewModel
import com.example.igor.deezclient.application.DeezApplication
import com.example.igor.deezclient.utils.SingleLiveEvent

open class BaseViewModel<T>(application: DeezApplication) : AndroidViewModel(application) {

    var errorsLiveData: SingleLiveEvent<T> = SingleLiveEvent()
}