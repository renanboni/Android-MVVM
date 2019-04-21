package com.boni.neon.ui.sendmoney

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.boni.domain.usecases.SendMoney
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class SendMoneyVMFactory @Inject constructor(
    private val sendMoney: SendMoney
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SendMoneyViewModel(sendMoney) as T
    }
}