package com.boni.neon.di.sendmoney

import com.boni.PostExecutionThread
import com.boni.domain.NeonRepository
import com.boni.neon.ui.sendmoney.SendMoneyVMFactory
import com.boni.usecases.SendMoney
import dagger.Module
import dagger.Provides

@Module
class SendMoneyModule {

    @Provides
    fun provideSendMoney(
        neonRepository: NeonRepository,
        postExecutionThread: PostExecutionThread
    ): SendMoney {
        return SendMoney(neonRepository, postExecutionThread)
    }

    @Provides
    fun provideSendMoneyVMFactory(sendMoney: SendMoney): SendMoneyVMFactory {
        return SendMoneyVMFactory(sendMoney)
    }
}