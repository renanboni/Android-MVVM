package com.boni.neon.ui.home

import com.boni.neon.entities.UserView

data class HomeViewState (
    val isLoading: Boolean = true,
    val userView: UserView? = null
)