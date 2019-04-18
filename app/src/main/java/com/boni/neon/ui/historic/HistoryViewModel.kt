package com.boni.neon.ui.historic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boni.domain.entities.ContactEntity
import com.boni.domain.entities.TransferEntity
import com.boni.neon.entities.ContactView
import com.boni.neon.entities.GraphView
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

            val history = mutableListOf<ContactView>()
            val chart = mutableListOf<GraphView>()

            transfers.forEach { t ->
                contacts.find { t.clientId == it.id }?.let {
                    it.value = t.value
                    history.add(mapper.mapToView(it))
                }
            }

            val group = history.groupBy {
                it.id
            }

            group.entries.forEach {
                var value = 0F

                it.value.forEach { c ->
                    value += c.value
                }

                val chartItem = GraphView(it.value.first().avatar, value)
                chart.add(chartItem)
            }

            val reversed = chart
                .sortedWith(compareBy { it.value })
                .asReversed()
                .toMutableList()

            val newState = viewState.value?.copy(
                isLoading = false,
                contacts = history,
                chart = reversed
            )

            viewState.value = newState
        }

        override fun onError(e: Throwable) {
            error.value = e
        }
    }
}