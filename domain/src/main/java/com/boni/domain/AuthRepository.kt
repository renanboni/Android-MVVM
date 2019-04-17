package com.boni.domain

import com.boni.domain.entities.UserEntity
import io.reactivex.Observable

interface AuthRepository {
    fun getUser(
        email: String,
        name: String
    ): Observable<UserEntity>
}