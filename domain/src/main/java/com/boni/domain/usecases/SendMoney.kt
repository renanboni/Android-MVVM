package com.boni.domain.usecases

import com.boni.PostExecutionThread
import com.boni.domain.NeonRepository
import io.reactivex.Observable

class SendMoney (
    private val neonRepository: NeonRepository,
    postExecutionThread: PostExecutionThread
): UseCase<Boolean, SendMoney.Params?>(postExecutionThread) {
    public override fun buildUseCaseObservable(params: Params?): Observable<Boolean> {
        if(params == null) {
            throw IllegalArgumentException("You must provide value and clientId in order to send money!")
        }

        return neonRepository.sendMoney(
            params.clientId,
            params.value
        )
    }

    data class Params constructor(
        val clientId: String,
        val value: Float
    ) {
        companion object {
            fun forSendMoney(
                clientId: String,
                value: Float
            ): Params {
                return Params(clientId, value)
            }
        }
    }
}