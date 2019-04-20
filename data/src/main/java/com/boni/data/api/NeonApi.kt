package com.boni.data.api

import com.boni.data.entities.ContactData
import com.boni.data.entities.TransferData
import io.reactivex.Observable
import retrofit2.http.*

interface NeonApi {

    @GET("Contacts")
    fun getContacts(
        @Header("x-auth-token")
        authToken: String
    ): Observable<MutableList<ContactData>>

    @GET("GetTransfers")
    fun getTransfers(
        @Header("x-auth-token")
        authToken: String
    ): Observable<MutableList<TransferData>>

    @FormUrlEncoded
    @POST("SendMoney")
    fun sendMoney(
        @Header("x-auth-token") authToken: String,
        @Field("clientId") clientId: String,
        @Field("valor") value: Float
    ): Observable<Boolean>
}