package com.boni.neon.ui.historic

import com.boni.neon.entities.ContactView

data class HistoryViewState (
    val isLoading: Boolean = true,
    val contacts: MutableList<ContactView>? = null
)