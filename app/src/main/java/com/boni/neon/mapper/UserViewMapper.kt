package com.boni.neon.mapper

import com.boni.domain.entities.UserEntity
import com.boni.neon.entities.UserView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserViewMapper @Inject constructor(): Mapper<UserEntity, UserView> {
    override fun mapToView(domain: UserEntity): UserView {
        return UserView(
            domain.name,
            domain.email,
            domain.avatar
        )
    }
}