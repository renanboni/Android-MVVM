package com.boni.neon.dagger.sendmoney

import com.boni.neon.ui.contacts.SendMoneyFragment
import dagger.Subcomponent

@SendMoneyScope
@Subcomponent(modules = [SendMoneyModule::class])
interface SendMoneySubComponent {
    fun inject(sendMoneyFragment: SendMoneyFragment)
}