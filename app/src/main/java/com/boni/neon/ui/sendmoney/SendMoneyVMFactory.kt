package com.boni.neon.ui.sendmoney

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.boni.neon.mapper.ContactViewMapper
import com.boni.usecases.GetContacts
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class SendMoneyVMFactory @Inject constructor(
    private val getContacts: GetContacts,
    private val mapper: ContactViewMapper
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SendMoneyViewModel(getContacts, mapper) as T
    }
}