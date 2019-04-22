package com.boni.neon.mapper

import com.boni.neon.test.DataFactory
import org.junit.Assert.*
import org.junit.Test

class ContactViewMapperTest {

    private val contactViewMapper = ContactViewMapper()

    @Test
    fun mapContactEntityToMapView() {
        val contactEntity = DataFactory.makeContactEntity()
        val contactView = contactViewMapper.mapToView(contactEntity)

        assertEquals(contactEntity.id, contactView.id)
        assertEquals(contactEntity.phone, contactView.phone)
        assertEquals(contactEntity.avatar, contactView.avatar)
        assertEquals(contactEntity.name, contactView.name)
    }
}