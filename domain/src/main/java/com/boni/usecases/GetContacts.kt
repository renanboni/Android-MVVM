package com.boni.usecases

import com.boni.PostExecutionThread
import com.boni.domain.NeonRepository
import com.boni.domain.entities.ContactEntity
import io.reactivex.Observable

class GetContacts (
    private val neonRepository: NeonRepository,
    postExecutionThread: PostExecutionThread
): UseCase<MutableList<ContactEntity>, Nothing?>(postExecutionThread) {
    override fun buildUseCaseObservable(params: Nothing?): Observable<MutableList<ContactEntity>> {
        return neonRepository.getContacts()
    }
}