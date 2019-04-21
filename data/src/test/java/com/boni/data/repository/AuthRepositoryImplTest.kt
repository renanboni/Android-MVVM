package com.boni.data.repository

import com.boni.data.TokenManager
import com.boni.data.api.AuthApi
import com.boni.data.api.NeonApi
import com.boni.data.entities.UserData
import com.boni.data.factory.DataFactory
import com.boni.data.mapper.UserMapper
import com.boni.domain.entities.UserEntity
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class AuthRepositoryImplTest {

    @Mock
    lateinit var authApi: AuthApi

    @Mock
    lateinit var userMapper: UserMapper

    @Mock
    lateinit var tokenManager: TokenManager

    private lateinit var authRepositoryImpl: AuthRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        authRepositoryImpl = AuthRepositoryImpl(
            authApi,
            userMapper,
            tokenManager
        )
    }

    @Test
    fun getUserReturnsData() {
        val response = DataFactory.makeUserData()
        val email = "renan.boni@usp.br"
        val name = "Renan"

        stubGetUserAuthApi(email, name, Observable.just(response))

        val userEntity = DataFactory.makeUserEntity()
        stubUserMapper(response, userEntity)

        val testObserver = authRepositoryImpl.getUser(
            email,
            name
        ).test()
        testObserver.assertValue(userEntity)
    }

    @Test
    fun getUserCompletes() {
        val response = DataFactory.makeUserData()
        val email = "renan.boni@usp.br"
        val name = "Renan"

        stubGetUserAuthApi(email, name, Observable.just(response))

        val userEntity = DataFactory.makeUserEntity()
        stubUserMapper(response, userEntity)

        val testObserver = authRepositoryImpl.getUser(
            email,
            name
        ).test()
        testObserver.assertComplete()
    }

    private fun stubGetUserAuthApi(
        email: String,
        name: String,
        observable: Observable<UserData>
    ) {
        whenever(authApi.authenticate(email, name)).thenReturn(observable)
    }

    private fun stubUserMapper(
        userData: UserData,
        userEntity: UserEntity
    ) {
        whenever(userMapper.mapFromModel(userData)).thenReturn(userEntity)
    }
}