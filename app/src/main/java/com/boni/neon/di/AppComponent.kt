package com.boni.neon.di

import com.boni.neon.di.modules.AppModule
import com.boni.neon.di.home.HomeModule
import com.boni.neon.di.home.HomeSubComponent
import com.boni.neon.di.modules.DataModule
import com.boni.neon.di.modules.NetworkModule
import com.boni.neon.di.contacts.ContactsModule
import com.boni.neon.di.contacts.ContactsSubComponent
import com.boni.neon.di.historic.HistoricModule
import com.boni.neon.di.historic.HistoricSubComponent
import com.boni.neon.di.sendmoney.SendMoneyModule
import com.boni.neon.di.sendmoney.SendMoneySubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, DataModule::class])
interface AppComponent {
    fun plus(homeModule: HomeModule): HomeSubComponent
    fun plus(contactsModule: ContactsModule): ContactsSubComponent
    fun plus(historicModule: HistoricModule): HistoricSubComponent
    fun plus(sendMoneyModule: SendMoneyModule): SendMoneySubComponent
}