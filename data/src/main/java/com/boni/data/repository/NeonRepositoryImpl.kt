package com.boni.data.repository

import com.boni.data.api.NeonApi
import com.boni.data.mapper.ContactMapper
import com.boni.domain.NeonRepository
import com.boni.domain.entities.ContactEntity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class NeonRepositoryImpl (
    private val api: NeonApi,
    private val mapper: ContactMapper
): NeonRepository {

    override fun getContacts(): Observable<MutableList<ContactEntity>> {
        return api.getContacts()
            .subscribeOn(Schedulers.io())
            .map { it.map { mapper.mapFromModel(it) }.toMutableList() }
    }

    override fun sendMoney() {

    }

}