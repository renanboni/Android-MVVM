package com.boni.neon.ui.historic

import com.boni.neon.entities.ContactView
import com.boni.neon.entities.GraphView

data class HistoryViewState (
    val isLoading: Boolean = true,
    val contacts: MutableList<ContactView>? = null,
    val chart: MutableList<GraphView>? = null
)