package com.boni.data.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {

    @GET("GenerateToken")
    fun authenticate(
        @Path("email") email: String,
        @Path("name") name: String
    ): Observable<UserResult>


}