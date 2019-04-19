package com.boni.neon.ui.sendmoney

import androidx.lifecycle.ViewModel
import com.boni.usecases.SendMoney
import javax.inject.Inject

class SendMoneyViewModel @Inject constructor(
    private val sendMoney: SendMoney
) : ViewModel() {


}