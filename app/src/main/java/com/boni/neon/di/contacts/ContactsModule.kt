package com.boni.neon.di.contacts

import com.boni.PostExecutionThread
import com.boni.domain.NeonRepository
import com.boni.neon.mapper.ContactViewMapper
import com.boni.neon.ui.contacts.ContactsVMFactory
import com.boni.domain.usecases.GetContacts
import dagger.Module
import dagger.Provides

@Module
class ContactsModule {

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
    ): ContactsVMFactory {
        return ContactsVMFactory(getContacts, mapper)
    }
}