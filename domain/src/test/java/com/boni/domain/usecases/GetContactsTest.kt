package com.boni.domain.usecases

import com.boni.PostExecutionThread
import com.boni.domain.NeonRepository
import com.boni.domain.entities.ContactEntity
import com.boni.domain.factory.ContactDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetContactsTest {

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Mock
    lateinit var neonRepository: NeonRepository

    private lateinit var getContacts: GetContacts

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getContacts = GetContacts(neonRepository, postExecutionThread)
    }

    @Test
    fun getContactsReturnsData() {
        val contacts = ContactDataFactory.makeContactList(10)
        stubGetContacts(Observable.just(contacts))
        val testObserver = getContacts.buildUseCaseObservable().test()
        testObserver.assertValue(contacts)
    }

    @Test
    fun getContactsComplete() {
        stubGetContacts(Observable.just(ContactDataFactory.makeContactList(10)))
        val testObserver = getContacts.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    private fun stubGetContacts(observable: Observable<MutableList<ContactEntity>>) {
        whenever(neonRepository.getContacts()).thenReturn(observable)
    }
}