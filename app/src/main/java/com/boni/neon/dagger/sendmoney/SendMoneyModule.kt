package com.boni.neon.dagger.sendmoney

import com.boni.PostExecutionThread
import com.boni.domain.NeonRepository
import com.boni.neon.mapper.ContactViewMapper
import com.boni.neon.ui.sendmoney.SendMoneyVMFactory
import com.boni.usecases.GetContacts
import dagger.Module
import dagger.Provides

@Module
class SendMoneyModule {

    @Provides
    fun provideGetContacts(
        neonRepository: NeonRepository,
        postExecutionThread: PostExecutionThread
    ): GetContacts {
        return GetContacts(neonRepository, postExecutionThread)
    }

    @Provides
    fun provideSendMoneyVMFactory(
        getContacts: GetContacts,
        mapper: ContactViewMapper
    ): SendMoneyVMFactory {
        return SendMoneyVMFactory(getContacts, mapper)
    }
}