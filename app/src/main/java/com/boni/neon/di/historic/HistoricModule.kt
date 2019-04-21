package com.boni.neon.di.historic

import com.boni.PostExecutionThread
import com.boni.domain.NeonRepository
import com.boni.neon.mapper.ContactViewMapper
import com.boni.neon.ui.historic.HistoryVMFactory
import com.boni.domain.usecases.GetTransfers
import dagger.Module
import dagger.Provides

@Module
class HistoricModule {

    @Provides
    fun providesGetTransfers(
        neonRepository: NeonRepository,
        postExecutionThread: PostExecutionThread
    ): GetTransfers {
        return GetTransfers(neonRepository, postExecutionThread)
    }

    @Provides
    fun provideHistoricVMFactory(
        getTransfers: GetTransfers,
        mapper: ContactViewMapper
    ): HistoryVMFactory {
        return HistoryVMFactory(getTransfers, mapper)
    }
}