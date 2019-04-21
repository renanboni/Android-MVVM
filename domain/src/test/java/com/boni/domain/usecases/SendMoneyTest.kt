package com.boni.domain.usecases

import com.boni.PostExecutionThread
import com.boni.domain.NeonRepository
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SendMoneyTest {

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Mock
    lateinit var neonRepository: NeonRepository

    private lateinit var sendMoney: SendMoney

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        sendMoney = SendMoney(
            neonRepository,
            postExecutionThread
        )
    }

    @Test
    fun sendMoneyReturnsData() {
        val clientId = "10"
        val value = 10f

        stubSendMoney(clientId, value, Observable.just(true))
        val testObserver = sendMoney.buildUseCaseObservable(SendMoney.Params.forSendMoney(clientId, value)).test()
        testObserver.assertValue(true)
    }

    @Test
    fun sendMoneyCompletes() {
        val clientId = "10"
        val value = 10f

        stubSendMoney(clientId, value, Observable.just(true))
        val testObserver = sendMoney.buildUseCaseObservable(SendMoney.Params.forSendMoney(clientId, value)).test()
        testObserver.assertComplete()
    }

    private fun stubSendMoney(clientId: String, value: Float, observable: Observable<Boolean>) {
        whenever(neonRepository.sendMoney(clientId, value)).thenReturn(observable)
    }
}