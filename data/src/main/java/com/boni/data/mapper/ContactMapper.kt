package com.boni.data.mapper

import com.boni.data.entities.ContactData
import com.boni.domain.entities.ContactEntity

class ContactMapper: ModelMapper<ContactData, ContactEntity> {
    override fun mapFromModel(model: ContactData): ContactEntity {
        return ContactEntity(
            id = model.id,
            name = model.name,
            avatar = model.avatar,
            phone = model.phone
        )
    }
}