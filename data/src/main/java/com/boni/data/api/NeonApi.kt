package com.boni.data.api

import com.boni.data.entities.ContactData
import io.reactivex.Observable
import retrofit2.http.GET

interface NeonApi {

    @GET("Contacts")
    fun getContacts(): Observable<MutableList<ContactData>>

    fun sendMoney()
}