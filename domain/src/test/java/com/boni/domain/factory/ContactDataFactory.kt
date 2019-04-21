package com.boni.domain.factory

import com.boni.domain.entities.ContactEntity
import com.boni.domain.entities.TransferEntity
import com.boni.domain.entities.UserEntity
import java.util.*

object ContactDataFactory {

    private fun randomString() : String {
        return UUID.randomUUID().toString()
    }

    private fun makeContact(): ContactEntity {
        return ContactEntity(
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            1f
        )
    }

    private fun makeTransfer(): TransferEntity {
        return TransferEntity(
            randomString(),
            randomString(),
            100f,
            randomString(),
            randomString()
        )
    }

    fun makeTransferList(count: Int): MutableList<TransferEntity> {
        val transfers = mutableListOf<TransferEntity>()

        repeat(count) {
            transfers.add(makeTransfer())
        }

        return transfers
    }

    fun makeContactList(count: Int): MutableList<ContactEntity> {
        val contacts = mutableListOf<ContactEntity>()

        repeat(count) {
            contacts.add(makeContact())
        }

        return contacts
    }

    fun makeUser(): UserEntity {
        return UserEntity(
            randomString(),
            randomString(),
            randomString(),
            randomString()
        )
    }
}