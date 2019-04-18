package com.boni.neon.mapper

import com.boni.domain.entities.ContactEntity
import com.boni.neon.entities.ContactView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactViewMapper @Inject constructor() : Mapper<ContactEntity, ContactView> {
    override fun mapToView(domain: ContactEntity): ContactView {
        return ContactView(
            id = domain.id,
            avatar = domain.avatar,
            name = domain.name,
            phone = domain.phone,
            value = domain.value
        )
    }
}