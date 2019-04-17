package com.boni.neon

import android.app.Application
import com.boni.neon.dagger.AppComponent
import com.boni.neon.dagger.DaggerAppComponent
import com.boni.neon.dagger.home.HomeModule
import com.boni.neon.dagger.home.HomeSubComponent
import com.boni.neon.dagger.modules.AppModule
import com.boni.neon.dagger.modules.NetworkModule

class NeonApplication: Application() {

    lateinit var appComponent: AppComponent

    private var homeSubComponent: HomeSubComponent? = null

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule())
            .networkModule(NetworkModule("http://teste"))
            .build()
    }

    fun createHomeComponent(): HomeSubComponent? {
        homeSubComponent = appComponent.plus(HomeModule())
        return homeSubComponent
    }
}