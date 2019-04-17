package com.boni.neon.dagger.modules

import com.boni.data.api.AuthApi
import com.boni.data.mapper.UserMapper
import com.boni.data.repository.AuthRepositoryImpl
import com.boni.domain.AuthRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        authApi: AuthApi,
        userMapper: UserMapper
    ): AuthRepository = AuthRepositoryImpl(authApi, userMapper)
}