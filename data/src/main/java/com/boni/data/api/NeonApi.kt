package com.boni.data.api

import com.boni.data.entities.ContactData
import com.boni.data.entities.TransferData
import io.reactivex.Observable
import retrofit2.http.GET

interface NeonApi {

    @GET("Contacts")
    fun getContacts(): Observable<MutableList<ContactData>>

    @GET("GetTransfers")
    fun getTransfers(): Observable<MutableList<TransferData>>

    fun sendMoney(): Observable<TransferData>
}