package com.boni.data.repository

import com.boni.data.TokenManager
import com.boni.data.api.NeonApi
import com.boni.data.entities.ContactData
import com.boni.data.factory.DataFactory
import com.boni.data.mapper.ContactMapper
import com.boni.data.mapper.TransferMapper
import com.boni.domain.entities.ContactEntity
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
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
    fun getContactsReturnData() {
        val response = DataFactory.makeContactList(10)
        stubGetContactsNeonApi(Observable.just(response))

        val entities = mutableListOf<ContactEntity>()

        response.forEach {
            val entity = DataFactory.makeContactEntity()
            entities.add(entity)

            stubContactMapper(it, entity)
        }

        val testObserver = neonRepositoryImpl.getContacts().test()
        testObserver.assertValue(entities)
    }

    private fun stubGetContactsNeonApi(observable: Observable<MutableList<ContactData>>) {
        whenever(neonApi.getContacts(any())).thenReturn(observable)
    }

    private fun stubContactMapper(
        contactData: ContactData,
        contactEntity: ContactEntity
    ) {
        whenever(contactMapper.mapFromModel(contactData)).thenReturn(contactEntity)
    }
}