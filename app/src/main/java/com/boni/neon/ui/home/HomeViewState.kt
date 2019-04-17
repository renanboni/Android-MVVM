package com.boni.neon.ui.home

import com.boni.neon.entities.User

data class HomeViewState (
    val isLoading: Boolean = true,
    val user: User? = null
)