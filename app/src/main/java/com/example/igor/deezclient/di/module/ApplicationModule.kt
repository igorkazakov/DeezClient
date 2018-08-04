package com.igorkazakov.user.foursquareclient.di.module

import android.content.Context
import com.example.igor.deezclient.application.DeezApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val mApplication: DeezApplication) {

    @Singleton
    @Provides
    fun provideApplication() : DeezApplication {
        return mApplication
    }

    @Singleton
    @Provides
    fun provideContext() : Context {
        return mApplication
    }
}