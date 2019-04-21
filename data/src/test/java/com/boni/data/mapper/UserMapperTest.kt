package com.boni.data.mapper

import com.boni.data.factory.DataFactory
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserMapperTest {

    private lateinit var userMapper: UserMapper

    @Before
    fun setup() {
        userMapper = UserMapper()
    }

    @Test
    fun mapUserDataToUserEntity() {
        val userData = DataFactory.makeUserData()
        val userEntity = userMapper.mapFromModel(userData)

        assertEquals(userData.name, userEntity.name)
        assertEquals(userData.email, userEntity.email)
        assertEquals(userData.avatar, userEntity.avatar)
        assertEquals(userData.token, userEntity.token)
    }
}