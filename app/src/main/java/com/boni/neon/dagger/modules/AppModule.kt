package com.boni.neon.dagger.modules

import android.content.Context
import com.boni.PostExecutionThread
import com.boni.neon.UiThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(context: Context) = context

    @Provides
    @Singleton
    fun providePostExecutionThread(): PostExecutionThread = UiThread()
}