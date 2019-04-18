package com.boni.neon.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boni.domain.entities.UserEntity
import com.boni.neon.mapper.UserViewMapper
import com.boni.usecases.GetUser
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class HomeViewModel @Inject constructor (
    private val getUser: GetUser,
    private val mapper: UserViewMapper
) : ViewModel() {

    var viewState: MutableLiveData<HomeViewState> = MutableLiveData()
    var error: MutableLiveData<Throwable?> = MutableLiveData()

    init {
        this.viewState.value = HomeViewState()
    }

    fun authenticate(
        email: String,
        name: String
    ) {
        getUser.execute(
            GetUserSubscriber(),
            GetUser.Params.forUser(email, name)
        )
    }

    inner class GetUserSubscriber : DisposableObserver<UserEntity>() {
        override fun onNext(t: UserEntity) {
            val newState = viewState.value?.copy(
                isLoading = false,
                userView = mapper.mapToView(t)
            )

            viewState.value = newState
        }

        override fun onComplete() {
            val newState = viewState.value?.copy(
                isLoading = false
            )

            viewState.value = newState
        }

        override fun onError(e: Throwable) {
            error.value = e
        }
    }
}