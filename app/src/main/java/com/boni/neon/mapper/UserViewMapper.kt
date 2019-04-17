package com.boni.neon.mapper

import com.boni.domain.entities.UserEntity
import com.boni.neon.entities.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserViewMapper @Inject constructor(): Mapper<UserEntity, User> {
    override fun mapToView(domain: UserEntity): User {
        return User(
            domain.name,
            domain.email,
            domain.avatar
        )
    }
}