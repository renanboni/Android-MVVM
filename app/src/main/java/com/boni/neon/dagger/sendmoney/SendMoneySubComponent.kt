package com.boni.neon.dagger.sendmoney

import com.boni.neon.ui.sendmoney.SendMoneyFragment
import dagger.Subcomponent

@SendMoneyScope
@Subcomponent(modules = [SendMoneyModule::class])
interface SendMoneySubComponent {
    fun inject(sendMoneyFragment: SendMoneyFragment)
}