package com.boni.neon.ui.contacts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boni.domain.entities.ContactEntity
import com.boni.neon.mapper.ContactViewMapper
import com.boni.domain.usecases.GetContacts
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class ContactsVieModel @Inject constructor(
    private val getContacts: GetContacts,
    private val mapper: ContactViewMapper
): ViewModel() {

    var viewState: MutableLiveData<ContactsViewState> = MutableLiveData()
    var error: MutableLiveData<Throwable?> = MutableLiveData()

    init {
        this.viewState.value = ContactsViewState()
    }

    fun getContacts() {
        getContacts.execute(GetContactsSubscriber())
    }

    inner class GetContactsSubscriber: DisposableObserver<MutableList<ContactEntity>>() {
        override fun onComplete() {
            val newState = viewState.value?.copy(
                isLoading = false
            )

            viewState.value = newState
        }

        override fun onNext(t: MutableList<ContactEntity>) {
            val newState = viewState.value?.copy(
                isLoading = false,
                contacts = t.map { mapper.mapToView(it) }.toMutableList()
            )

            viewState.value = newState
        }

        override fun onError(e: Throwable) {
            error.value = e
        }
    }
}