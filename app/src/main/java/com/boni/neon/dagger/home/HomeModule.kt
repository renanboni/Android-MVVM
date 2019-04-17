package com.boni.neon.dagger.home

import com.boni.PostExecutionThread
import com.boni.domain.AuthRepository
import com.boni.neon.mapper.UserViewMapper
import com.boni.neon.ui.home.HomeVMFactory
import com.boni.usecases.GetUser
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    fun provideGetUser(authRepository: AuthRepository,
                       postExecutionThread: PostExecutionThread): GetUser {
        return GetUser(authRepository, postExecutionThread)
    }

    @Provides
    fun provideHomeVMFactory(
        getUser: GetUser,
        mapper: UserViewMapper
    ) = HomeVMFactory(getUser, mapper)
}