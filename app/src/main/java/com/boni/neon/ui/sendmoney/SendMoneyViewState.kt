package com.boni.neon.ui.sendmoney

import com.boni.neon.entities.ContactView

data class SendMoneyViewState (
    val isLoading: Boolean = true,
    val contacts: MutableList<ContactView>? = null
)