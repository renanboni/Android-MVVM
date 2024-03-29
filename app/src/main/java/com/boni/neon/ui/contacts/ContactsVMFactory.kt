package com.boni.neon.ui.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.boni.neon.mapper.ContactViewMapper
import com.boni.domain.usecases.GetContacts
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ContactsVMFactory @Inject constructor(
    private val getContacts: GetContacts,
    private val mapper: ContactViewMapper
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ContactsVieModel(getContacts, mapper) as T
    }
}