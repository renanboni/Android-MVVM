package com.boni.neon.ui.historic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.boni.neon.mapper.ContactViewMapper
import com.boni.usecases.GetTransfers
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class HistoryVMFactory @Inject constructor(
    private val getTransfers: GetTransfers,
    private val mapper: ContactViewMapper
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HistoryViewModel(getTransfers, mapper) as T
    }
}