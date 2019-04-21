package com.boni.data.mapper

import com.boni.data.factory.DataFactory
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ContactMapperTest {

    private lateinit var mapper: ContactMapper

    @Before
    fun setup() {
        mapper = ContactMapper()
    }

    @Test
    fun mapContactDataToEntity() {
        val contactData = DataFactory.makeContactData()
        val contactEntity = mapper.mapFromModel(contactData)

        assertEquals(contactData.id, contactEntity.id)
        assertEquals(contactData.name, contactEntity.name)
        assertEquals(contactData.avatar, contactEntity.avatar)
        assertEquals(contactData.phone, contactEntity.phone)
    }
}