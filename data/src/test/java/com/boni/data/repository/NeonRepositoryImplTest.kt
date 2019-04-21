package com.boni.data.repository

import com.boni.data.TokenManager
import com.boni.data.api.NeonApi
import com.boni.data.entities.ContactData
import com.boni.data.entities.TransferData
import com.boni.data.factory.DataFactory
import com.boni.data.mapper.ContactMapper
import com.boni.data.mapper.TransferMapper
import com.boni.domain.entities.ContactEntity
import com.boni.domain.entities.TransferEntity
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NeonRepositoryImplTest {

    @Mock
    lateinit var neonApi: NeonApi

    @Mock
    lateinit var contactMapper: ContactMapper

    @Mock
    lateinit var transferMapper: TransferMapper

    @Mock
    lateinit var tokenManager: TokenManager

    private lateinit var neonRepositoryImpl: NeonRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        neonRepositoryImpl = NeonRepositoryImpl(
            neonApi,
            contactMapper,
            transferMapper,
            tokenManager
        )
    }

    @Test
    fun sendMoneyReturnsData() {
        val response = Observable.just(true)
        val token = "token"
        val clientId = "10"
        val value = 10f

        stubSendMoneyNeonApi(token, clientId, value, response)
        stubTokenManager(token)

        val testObserver = neonRepositoryImpl.sendMoney(clientId, value).test()
        testObserver.assertValue(true)
    }

    @Test
    fun sendMoneyCompletes() {
        val token = "token"
        val clientId = "10"
        val value = 10f

        stubSendMoneyNeonApi(token, clientId, value, Observable.just(true))
        stubTokenManager(token)

        val testObserver = neonRepositoryImpl.sendMoney(clientId, value).test()
        testObserver.assertComplete()
    }

    @Test
    fun getContactsReturnsData() {
        val response = DataFactory.makeContactList(10)
        val token = "token"

        stubGetContactsNeonApi(token, Observable.just(response))
        stubTokenManager(token)

        val entities = mutableListOf<ContactEntity>()

        response.forEach {
            val entity = DataFactory.makeContactEntity()
            entities.add(entity)

            stubContactMapper(it, entity)
        }

        val testObserver = neonRepositoryImpl.getContacts().test()
        testObserver.assertValue(entities)
    }

    @Test
    fun getContactsCompletes() {
        val token = "token"

        stubGetContactsNeonApi(token, Observable.just(DataFactory.makeContactList(10)))
        stubTokenManager(token)
        stubContactMapper(any(), DataFactory.makeContactEntity())

        val testObserver = neonRepositoryImpl.getContacts().test()
        testObserver.assertComplete()
    }

    @Test
    fun getTransfersReturnData() {
        val response = DataFactory.makeTransferList(10)
        val token = "token"

        stubGetTransfersNeonApi(token, Observable.just(response))
        stubTokenManager(token)

        val entities = mutableListOf<TransferEntity>()

        response.forEach {
            val entity = DataFactory.makeTransferEntity()
            entities.add(entity)

            stubTransferMapper(it, entity)
        }

        val testObserver = neonRepositoryImpl.getTransfers().test()
        testObserver.assertValue(entities)
    }

    @Test
    fun getTransfersCompletes() {
        val token = "token"

        stubGetTransfersNeonApi(token, Observable.just(DataFactory.makeTransferList(10)))
        stubTokenManager(token)
        stubTransferMapper(any(), DataFactory.makeTransferEntity())

        val testObserver = neonRepositoryImpl.getTransfers().test()
        testObserver.assertComplete()
    }

    private fun stubGetContactsNeonApi(
        token: String,
        observable: Observable<MutableList<ContactData>>
    ) {
        whenever(neonApi.getContacts(token)).thenReturn(observable)
    }

    private fun stubGetTransfersNeonApi(
        token: String,
        observable: Observable<MutableList<TransferData>>
    ) {
        whenever(neonApi.getTransfers(token)).thenReturn(observable)
    }

    private fun stubSendMoneyNeonApi(
        token: String,
        clientId: String,
        value: Float,
        observable: Observable<Boolean>
    ) {
        whenever(neonApi.sendMoney(token, clientId, value)).thenReturn(observable)
    }

    private fun stubContactMapper(
        contactData: ContactData,
        contactEntity: ContactEntity
    ) {
        whenever(contactMapper.mapFromModel(contactData)).thenReturn(contactEntity)
    }

    private fun stubTransferMapper(
        transferData: TransferData,
        transferEntity: TransferEntity
    ) {
        whenever(transferMapper.mapFromModel(transferData)).thenReturn(transferEntity)
    }

    private fun stubTokenManager(token: String) {
        whenever(tokenManager.retrieveToken()).thenReturn(token)
    }
}