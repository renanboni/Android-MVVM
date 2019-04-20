package com.boni.data.mapper

import com.boni.data.entities.UserData
import com.boni.domain.entities.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

class UserMapper : ModelMapper<UserData, UserEntity> {
    override fun mapFromModel(model: UserData): UserEntity {
        return UserEntity(
            model.name,
            model.email,
            model.avatar,
            model.token
        )
    }
}