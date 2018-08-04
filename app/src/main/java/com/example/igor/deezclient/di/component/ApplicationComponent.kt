package com.example.igor.deezclient.di.component


import com.example.igor.deezclient.application.DeezApplication
import com.example.igor.deezclient.viewModels.ViewModelFactory

import com.igorkazakov.user.foursquareclient.di.module.ApplicationModule
import com.igorkazakov.user.foursquareclient.di.module.DataServiceModule

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(DataServiceModule::class),
    (ApplicationModule::class)])
interface ApplicationComponent {

    fun inject(application: DeezApplication)
    fun inject(viewModelFactory: ViewModelFactory)
}