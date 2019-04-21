package com.boni.domain.usecases

import com.boni.PostExecutionThread
import com.boni.domain.NeonRepository
import com.boni.domain.entities.ContactEntity
import com.boni.domain.entities.TransferEntity
import com.boni.domain.factory.ContactDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetTransfersTest {

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Mock
    lateinit var neonRepository: NeonRepository

    private lateinit var getTransfers: GetTransfers

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getTransfers = GetTransfers(neonRepository, postExecutionThread)
    }

    @Test
    fun getTransfersReturnsData() {
        val transfers = ContactDataFactory.makeTransferList(10)
        val contacts = ContactDataFactory.makeContactList(10)
        stubTransfers(Observable.just(transfers))
        stubGetContacts(Observable.just(contacts))
        val testObserver = getTransfers.buildUseCaseObservable().test()
        testObserver.assertValue(Pair(contacts, transfers))
    }

    @Test
    fun getTransfersCompletes() {
        stubTransfers(Observable.just(ContactDataFactory.makeTransferList(10)))
        stubGetContacts(Observable.just(ContactDataFactory.makeContactList(10)))

        val testObserver = getTransfers.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    private fun stubTransfers(observable: Observable<MutableList<TransferEntity>>) {
        whenever(neonRepository.getTransfers()).thenReturn(observable)
    }

    private fun stubGetContacts(observable: Observable<MutableList<ContactEntity>>) {
        whenever(neonRepository.getContacts()).thenReturn(observable)
    }
}