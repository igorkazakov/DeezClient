package com.example.igor.deezclient.application

import android.app.Application
import com.example.igor.deezclient.di.component.ApplicationComponent
import com.example.igor.deezclient.di.component.DaggerApplicationComponent
import com.facebook.drawee.backends.pipeline.Fresco
import com.igorkazakov.user.foursquareclient.di.module.ApplicationModule

class DeezApplication : Application() {

    companion object {
        @JvmStatic lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()

        Fresco.initialize(this)
    }
}