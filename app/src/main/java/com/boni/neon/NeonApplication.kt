package com.boni.neon

import android.app.Application
import com.boni.neon.dagger.home.HomeSubComponent

class NeonApplication: Application() {

    private var homeSubComponent: HomeSubComponent? = null

    override fun onCreate() {
        super.onCreate()
    }
}