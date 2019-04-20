package com.boni.data

import com.boni.domain.entities.ContactEntity
import com.boni.domain.entities.TransferEntity
import com.boni.domain.entities.UserEntity
import com.boni.mock.MockInterceptor
import com.google.gson.Gson
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody

class MockData {
    companion object {

        private var transfers = mutableListOf<TransferEntity>()

        fun getUser(): UserEntity {
            return UserEntity("Renan", "renan.boni@usp.br", "https://cdn.iconscout.com/icon/free/png-256/avatar-372-456324.png", "1d40d305-c836-43a2-b4db-acc56bcc1393")
        }

        fun getContacts(): MutableList<ContactEntity> {
            val c1 = ContactEntity("1", "Renan Boni", "(18) 99137-0488", "https://cdn.iconscout.com/icon/free/png-256/avatar-372-456324.png")
            val c2 = ContactEntity("2", "Joao Antonio", "(11) 91234-0488", "https://cdn3.iconfinder.com/data/icons/business-avatar-1/512/10_avatar-512.png")
            val c3 = ContactEntity("3", "Joao Bernardo", "(11) 98482-0488", "https://cdn.iconscout.com/icon/free/png-256/avatar-372-456324.png")
            val c4 = ContactEntity("4", "Maria Luiza", "(16)99882-0488", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS1sfG6usLmkeShGnkAPRXcLwRLwSzXyWUEASrxGvCyDLB_3kdD")
            val c5 = ContactEntity("5", "Luiza Antonieta", "(18) 92022-1222", "https://cdn.iconscout.com/icon/free/png-256/avatar-369-456321.png")
            val c6 = ContactEntity("6", "Neymar Junior", "(19) 98722-1111", "https://cdn.pixabay.com/photo/2016/11/18/23/38/child-1837375_960_720.png")
            val c7 = ContactEntity("7", "Rogerio Ceni", "(22) 98221-1234", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS1sfG6usLmkeShGnkAPRXcLwRLwSzXyWUEASrxGvCyDLB_3kdD")
            val c8 = ContactEntity("8", "Palmeiras Nao", "(11) 91231-12334", "https://cdn3.iconfinder.com/data/icons/business-avatar-1/512/10_avatar-512.png")
            val c9 = ContactEntity("9", "Tem Mundial", "(18) 90828-12345", "https://cdn3.iconfinder.com/data/icons/avatars-15/64/_Bearded_Man-17-512.png")
            val c10 = ContactEntity("10", "Santo Amaro", "(27) 92832-0488", "https://cdn.pixabay.com/photo/2016/11/18/23/38/child-1837375_960_720.png")
            val c11 = ContactEntity("11", "Luis Antonio", "(18) 99137-0488", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS1sfG6usLmkeShGnkAPRXcLwRLwSzXyWUEASrxGvCyDLB_3kdD")
            val c12 = ContactEntity("12", "Almeida Antonio", "(18) 99137-0488", "https://cdn3.iconfinder.com/data/icons/avatars-15/64/_Bearded_Man-17-512.png")
            val c13 = ContactEntity("13", "Joao Antonio", "(11) 82822-0488", "https://cdn.pixabay.com/photo/2016/11/18/23/38/child-1837375_960_720.png")
            val c14 = ContactEntity("14", "Vitoria Antonia", "(18) 99999-1188", "https://cdn.iconscout.com/icon/free/png-256/avatar-369-456321.png")
            val c15 = ContactEntity("15", "Junior Nascimento", "(18) 92558-1188", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS1sfG6usLmkeShGnkAPRXcLwRLwSzXyWUEASrxGvCyDLB_3kdD")

            val contacts = mutableListOf<ContactEntity>()
            contacts.add(c1)
            contacts.add(c2)
            contacts.add(c3)
            contacts.add(c4)
            contacts.add(c5)
            contacts.add(c6)
            contacts.add(c7)
            contacts.add(c8)
            contacts.add(c9)
            contacts.add(c10)
            contacts.add(c11)
            contacts.add(c12)
            contacts.add(c13)
            contacts.add(c14)
            contacts.add(c15)

            return contacts
        }

        fun getTransfers(): MutableList<TransferEntity> {
            return transfers
        }

        fun addTransfer(
            clientId: String,
            value: Float
        ): Boolean {
            val transfer = TransferEntity("1", clientId, value, "token", "data")
            transfers.add(transfer)

            return true
        }
    }
}