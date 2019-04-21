package com.boni.data.factory

import com.boni.data.entities.ContactData
import com.boni.data.entities.TransferData
import com.boni.data.entities.UserData
import com.boni.domain.entities.ContactEntity
import java.util.*

object DataFactory {

    private fun randomString() : String {
        return UUID.randomUUID().toString()
    }

    fun makeContactList(count: Int): MutableList<ContactData> {
        val contacts = mutableListOf<ContactData>()

        repeat(count) {
            contacts.add(DataFactory.makeContactData())
        }

        return contacts
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
}