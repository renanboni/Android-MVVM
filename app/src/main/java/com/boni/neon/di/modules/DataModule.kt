package com.boni.neon.di.modules

import com.boni.data.TokenManager
import com.boni.data.api.AuthApi
import com.boni.data.api.NeonApi
import com.boni.data.mapper.ContactMapper
import com.boni.data.mapper.TransferMapper
import com.boni.data.mapper.UserMapper
import com.boni.data.repository.AuthRepositoryImpl
import com.boni.data.repository.NeonRepositoryImpl
import com.boni.domain.AuthRepository
import com.boni.domain.NeonRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        authApi: AuthApi,
        userMapper: UserMapper,
        tokenManager: TokenManager
    ): AuthRepository = AuthRepositoryImpl(
        authApi,
        userMapper,
        tokenManager
    )

    @Provides
    @Singleton
    fun provideNeonRepository(
        neonApi: NeonApi,
        contactMapper: ContactMapper,
        transferMapper: TransferMapper,
        tokenManager: TokenManager
    ): NeonRepository = NeonRepositoryImpl(
        neonApi,
        contactMapper,
        transferMapper,
        tokenManager
    )

    @Singleton
    @Provides
    fun provideTokenManager() = TokenManager()

    @Singleton
    @Provides
    fun provideUserMapper() = UserMapper()

    @Provides
    @Singleton
    fun provideContactMapper() = ContactMapper()

    @Singleton
    @Provides
    fun provideTransferMapper() = TransferMapper()
}