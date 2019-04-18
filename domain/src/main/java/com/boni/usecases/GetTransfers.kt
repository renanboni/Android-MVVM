package com.boni.usecases

import com.boni.PostExecutionThread
import com.boni.domain.NeonRepository
import com.boni.domain.entities.ContactEntity
import com.boni.domain.entities.TransferEntity
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class GetTransfers (
    private val neonRepository: NeonRepository,
    postExecutionThread: PostExecutionThread
): UseCase<Pair<MutableList<ContactEntity>, MutableList<TransferEntity>>, Nothing?>(postExecutionThread) {
    override fun buildUseCaseObservable(params: Nothing?): Observable<Pair<MutableList<ContactEntity>, MutableList<TransferEntity>>> {
        return Observable.zip(
            neonRepository.getContacts(),
            neonRepository.getTransfers(),
            BiFunction { t1, t2 ->
                Pair(t1, t2)
            }
        )
    }
}