package com.boni.data.api

import com.boni.data.entities.UserData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthApi {

    @GET("GenerateToken")
    fun authenticate(
        @Query("email") email: String,
        @Query("name") name: String
    ): Observable<UserData>
}