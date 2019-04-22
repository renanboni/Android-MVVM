package com.boni.neon

import android.app.Application
import com.boni.neon.di.AppComponent
import com.boni.neon.di.DaggerAppComponent
import com.boni.neon.di.home.HomeModule
import com.boni.neon.di.home.HomeSubComponent
import com.boni.neon.di.modules.NetworkModule
import com.boni.neon.di.contacts.ContactsModule
import com.boni.neon.di.contacts.ContactsSubComponent
import com.boni.neon.di.historic.HistoricModule
import com.boni.neon.di.historic.HistoricSubComponent
import com.boni.neon.di.sendmoney.SendMoneyModule
import com.boni.neon.di.sendmoney.SendMoneySubComponent

class NeonApplication: Application() {

    private lateinit var appComponent: AppComponent

    private var homeSubComponent: HomeSubComponent? = null
    private var contactsSubComponent: ContactsSubComponent? = null
    private var historicSubComponent: HistoricSubComponent? = null
    private var sendMoneySubComponent: SendMoneySubComponent? = null

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .networkModule(NetworkModule(BuildConfig.ENDPOINT))
            .build()
    }

    fun createHomeComponent(): HomeSubComponent? {
        if(homeSubComponent == null) {
            homeSubComponent = appComponent.plus(HomeModule())
        }
        return homeSubComponent
    }

    fun createContactsComponent(): ContactsSubComponent? {
        if(contactsSubComponent == null) {
            contactsSubComponent = appComponent.plus(ContactsModule())
        }
        return contactsSubComponent
    }

    fun createHistoricSubComponent(): HistoricSubComponent? {
        if(historicSubComponent == null) {
            historicSubComponent = appComponent.plus(HistoricModule())
        }

        return historicSubComponent
    }

    fun createSendMoneySubComponent(): SendMoneySubComponent? {
        if(sendMoneySubComponent == null) {
            sendMoneySubComponent = appComponent.plus(SendMoneyModule())
        }

        return sendMoneySubComponent
    }
}