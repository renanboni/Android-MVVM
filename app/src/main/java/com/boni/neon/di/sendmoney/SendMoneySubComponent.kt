package com.boni.neon.di.sendmoney

import com.boni.neon.di.contacts.ContactsScope
import com.boni.neon.ui.sendmoney.SendMoneyDialogFragment
import dagger.Subcomponent

@ContactsScope
@Subcomponent(modules = [SendMoneyModule::class])
interface SendMoneySubComponent {
    fun inject(sendMoneyDialogFragment: SendMoneyDialogFragment)
}