package com.boni.data.factory

import com.boni.data.entities.ContactData
import com.boni.data.entities.TransferData
import com.boni.data.entities.UserData
import com.boni.domain.entities.ContactEntity
import com.boni.domain.entities.TransferEntity
import com.boni.domain.entities.UserEntity
import java.util.*

object DataFactory {

    private fun randomString() : String {
        return UUID.randomUUID().toString()
    }

    fun makeUserEntity(): UserEntity {
        return UserEntity(
            randomString(),
            randomString(),
            randomString(),
            randomString()
        )
    }

    fun makeContactList(count: Int): MutableList<ContactData> {
        val contacts = mutableListOf<ContactData>()

        repeat(count) {
            contacts.add(makeContactData())
        }

        return contacts
    }

    fun makeTransferList(count: Int): MutableList<TransferData> {
        val transfers = mutableListOf<TransferData>()

        repeat(count) {
            transfers.add(makeTransfer())
        }

        return transfers
    }

    private fun makeTransfer(): TransferData {
        return TransferData(
            randomString(),
            randomString(),
            100f,
            randomString(),
            randomString()
        )
    }

    fun makeContactData(): ContactData {
        return ContactData(
            randomString(),
            randomString(),
            randomString(),
            randomString()
        )
    }

    fun makeTransferData(): TransferData {
        return TransferData(
            randomString(),
            randomString(),
            100f,
            randomString(),
            randomString()
        )
    }

    fun makeUserData(): UserData {
        return UserData(
            randomString(),
            randomString(),
            randomString(),
            randomString()
        )
    }

    fun makeContactEntity(): ContactEntity {
        return ContactEntity(
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            1f
        )
    }

    fun makeTransferEntity(): TransferEntity {
        return TransferEntity(
            randomString(),
            randomString(),
            100f,
            randomString(),
            randomString()
        )
    }
}