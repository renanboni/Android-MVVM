package com.boni.neon.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.boni.neon.mapper.UserViewMapper
import com.boni.usecases.GetUser

@Suppress("UNCHECKED_CAST")
class HomeVMFactory(
    private val getUser: GetUser,
    private val mapper: UserViewMapper
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(getUser, mapper) as T
    }
}