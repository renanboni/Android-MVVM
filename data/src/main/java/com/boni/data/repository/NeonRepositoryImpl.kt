package com.boni.data.repository

import com.boni.data.api.NeonApi
import com.boni.data.mapper.ContactMapper
import com.boni.data.mapper.TransferMapper
import com.boni.domain.NeonRepository
import com.boni.domain.entities.ContactEntity
import com.boni.domain.entities.TransferEntity
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class NeonRepositoryImpl (
    private val api: NeonApi,
    private val contactMapper: ContactMapper,
    private val transferMapper: TransferMapper
): NeonRepository {

    override fun getContacts(): Observable<MutableList<ContactEntity>> {
        return api.getContacts()
            .subscribeOn(Schedulers.io())
            .map { it.map { contactMapper.mapFromModel(it) }.toMutableList() }
    }

    override fun getTransfers(): Observable<MutableList<TransferEntity>> {
        return api.getTransfers()
            .subscribeOn(Schedulers.io())
            .map { it.map { transferMapper.mapFromModel(it) }.toMutableList() }
    }

    override fun sendMoney(): Observable<Boolean> {
        return api.sendMoney()
            .subscribeOn(Schedulers.io())
    }
}