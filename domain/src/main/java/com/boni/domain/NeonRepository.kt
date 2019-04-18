package com.boni.domain

import com.boni.domain.entities.ContactEntity
import io.reactivex.Observable

interface NeonRepository {
    fun getContacts(): Observable<MutableList<ContactEntity>>
    fun sendMoney()
}