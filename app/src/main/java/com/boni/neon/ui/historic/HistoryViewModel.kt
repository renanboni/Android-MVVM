package com.boni.neon.ui.historic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boni.domain.entities.ContactEntity
import com.boni.domain.entities.TransferEntity
import com.boni.neon.mapper.ContactViewMapper
import com.boni.usecases.GetTransfers
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val getTransfers: GetTransfers,
    private val mapper: ContactViewMapper
): ViewModel() {

    var viewState: MutableLiveData<HistoryViewState> = MutableLiveData()
    var error: MutableLiveData<Throwable?> = MutableLiveData()

    init {
        this.viewState.value = HistoryViewState()
    }

    fun getTransfers() {
        getTransfers.execute(GetTransfersSubscriber())
    }

    inner class GetTransfersSubscriber : DisposableObserver<Pair<MutableList<ContactEntity>, MutableList<TransferEntity>>>() {
        override fun onComplete() {
            val newState = viewState.value?.copy(
                isLoading = false
            )

            viewState.value = newState
        }

        override fun onNext(result: Pair<MutableList<ContactEntity>, MutableList<TransferEntity>>) {
            val contacts = result.first
            val transfers = result.second

            contacts.forEach { c ->
                transfers.find { it.clientId == c.id }?.let {
                    c.value = it.value
                }
            }

            val newState = viewState.value?.copy(
                isLoading = false,
                contacts = contacts.map { mapper.mapToView(it) }.toMutableList()
            )

            viewState.value = newState
        }

        override fun onError(e: Throwable) {
            error.value = e
        }
    }
}