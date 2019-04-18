package com.boni.neon.di.contacts

import com.boni.neon.ui.contacts.ContactsFragment
import dagger.Subcomponent

@ContactsScope
@Subcomponent(modules = [ContactsModule::class])
interface ContactsSubComponent {
    fun inject(contactsFragment: ContactsFragment)
}