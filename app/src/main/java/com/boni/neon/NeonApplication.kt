package com.boni.neon

import android.app.Application
import com.boni.neon.dagger.AppComponent
import com.boni.neon.dagger.DaggerAppComponent
import com.boni.neon.dagger.home.HomeModule
import com.boni.neon.dagger.home.HomeSubComponent
import com.boni.neon.dagger.modules.NetworkModule
import com.boni.neon.dagger.sendmoney.SendMoneyModule
import com.boni.neon.dagger.sendmoney.SendMoneySubComponent

class NeonApplication: Application() {

    private lateinit var appComponent: AppComponent

    private var homeSubComponent: HomeSubComponent? = null
    private var sendMoneySubComponent: SendMoneySubComponent? = null

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .networkModule(NetworkModule("http://processoseletivoneon.neonhomol.com.br"))
            .build()
    }

    fun createHomeComponent(): HomeSubComponent? {
        if(homeSubComponent == null) {
            homeSubComponent = appComponent.plus(HomeModule())
        }
        return homeSubComponent
    }

    fun createSendMoneyComponent(): SendMoneySubComponent? {
        if(sendMoneySubComponent == null) {
            sendMoneySubComponent = appComponent.plus(SendMoneyModule())
        }
        return sendMoneySubComponent
    }
}