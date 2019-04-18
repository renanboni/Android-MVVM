package com.boni.neon.ui.contacts

import com.boni.neon.entities.ContactView

data class ContactsViewState (
    val isLoading: Boolean = true,
    val contacts: MutableList<ContactView>? = null
)