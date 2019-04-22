package com.boni.neon.test

import com.boni.domain.entities.ContactEntity
import com.boni.domain.entities.UserEntity
import java.util.*

object DataFactory {

    private fun randomString() : String {
        return UUID.randomUUID().toString()
    }

    fun makeContactEntity(): ContactEntity {
        return ContactEntity(
            randomString(),
            randomString(),
            randomString(),
            randomString()
        )
    }

    fun makeUserEntity(): UserEntity {
        return UserEntity(
            randomString(),
            randomString(),
            randomString(),
            randomString()
        )
    }
}