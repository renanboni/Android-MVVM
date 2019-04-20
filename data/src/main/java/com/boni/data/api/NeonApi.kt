package com.boni.data.api

import com.boni.data.entities.ContactData
import com.boni.data.entities.TransferData
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface NeonApi {

    @GET("Contacts")
    fun getContacts(): Observable<MutableList<ContactData>>

    @GET("GetTransfers")
    fun getTransfers(): Observable<MutableList<TransferData>>

    @FormUrlEncoded
    @POST("SendMoney")
    fun sendMoney(
        @Field("clientId") clientId: String,
        @Field("valor") value: Float
    ): Observable<Boolean>
}