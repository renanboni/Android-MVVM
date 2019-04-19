package com.boni.domain

import com.boni.domain.entities.ContactEntity
import com.boni.domain.entities.TransferEntity
import io.reactivex.Observable

interface NeonRepository {
    fun getContacts(): Observable<MutableList<ContactEntity>>
    fun getTransfers(): Observable<MutableList<TransferEntity>>
    fun sendMoney(): Observable<Boolean>
}