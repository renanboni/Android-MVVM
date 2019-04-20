package com.boni.neon.ui.sendmoney

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boni.usecases.SendMoney
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class SendMoneyViewModel @Inject constructor(
    private val sendMoneyUseCase: SendMoney
) : ViewModel() {

    val viewState: MutableLiveData<SendMoneyState> = MutableLiveData()
    var error: MutableLiveData<Throwable?> = MutableLiveData()

    init {
        viewState.value = SendMoneyState()
    }

    var value: Float = 0f
    var clientId: String = ""

    fun sendMoney() {
        if(value <= 0f) {
            error.postValue(Throwable("O valor deve ser maior que zero."))
        } else {
            viewState.postValue(SendMoneyState(true))
            sendMoneyUseCase.execute(
                SendMoneySubscriber(),
                SendMoney.Params.forSendMoney(
                    clientId,
                    value
                )
            )
        }
    }

    inner class SendMoneySubscriber : DisposableObserver<Boolean>() {
        override fun onComplete() {
            val newState = viewState.value?.copy(
                isLoading = false
            )

            viewState.value = newState
        }

        override fun onNext(t: Boolean) {
            val newState = viewState.value?.copy(
                isLoading = false,
                moneySent = t
            )

            viewState.value = newState
        }

        override fun onError(e: Throwable) {
            error.value = e
        }
    }
}