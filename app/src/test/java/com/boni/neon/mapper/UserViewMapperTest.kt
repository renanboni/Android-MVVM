package com.boni.neon.mapper

import com.boni.neon.test.DataFactory
import org.junit.Assert.*
import org.junit.Test

class UserViewMapperTest {
    private val userViewMapper = UserViewMapper()

    @Test
    fun mapUserEntityToUserView() {
        val userEntity = DataFactory.makeUserEntity()
        val userView = userViewMapper.mapToView(userEntity)

        assertEquals(userEntity.name, userView.name)
        assertEquals(userEntity.avatar, userView.avatar)
        assertEquals(userEntity.email, userView.email)
    }
}