package com.boni.domain.usecases

import com.boni.PostExecutionThread
import com.boni.domain.AuthRepository
import com.boni.domain.entities.UserEntity
import com.boni.domain.test.ContactDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetUserTest {

    companion object {
        private const val EMAIL = "renan.boni@usp.br"
        private const val NAME = "Renan Boni"
    }

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Mock
    lateinit var authRepository: AuthRepository

    lateinit var getUser: GetUser

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getUser = GetUser(authRepository, postExecutionThread)
    }

    @Test
    fun getUserReturnsData() {
        val user = ContactDataFactory.makeUser()
        stubGetUser(Observable.just(user))
        val testObserver = getUser.buildUseCaseObservable(GetUser.Params.forUser(
            NAME,
            EMAIL
        )).test()
        testObserver.assertValue(user)
    }

    @Test
    fun getUserCompletes() {
        stubGetUser(Observable.just(ContactDataFactory.makeUser()))
        val testObserver = getUser.buildUseCaseObservable(GetUser.Params.forUser(
            NAME,
            EMAIL
        )).test()
        testObserver.assertComplete()
    }

    private fun stubGetUser(observable: Observable<UserEntity>) {
        whenever(authRepository.getUser(EMAIL, NAME)).thenReturn(observable)
    }
}