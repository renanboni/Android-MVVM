package com.boni.usecases

import com.boni.PostExecutionThread
import com.boni.domain.AuthRepository
import com.boni.domain.entities.UserEntity
import io.reactivex.Observable

class GetUser (
    private val authRepository: AuthRepository,
    postExecutionThread: PostExecutionThread
): UseCase<UserEntity, GetUser.Params?>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Observable<UserEntity> {
        if(params == null) {
            throw IllegalArgumentException("You must provide name and email in order to receive a user!")
        }

        return authRepository.getUser(params.email, params.name)
    }

    data class Params constructor(
        val name: String,
        val email: String
    ) {
        companion object {
            fun forUser(
                name: String,
                email: String
            ): Params {
                return Params(name, email)
            }
        }
    }
}