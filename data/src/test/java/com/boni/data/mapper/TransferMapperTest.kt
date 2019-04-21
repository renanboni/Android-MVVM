package com.boni.data.mapper

import com.boni.data.factory.DataFactory
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TransferMapperTest {

    private lateinit var transferMapper: TransferMapper

    @Before
    fun setup() {
        transferMapper = TransferMapper()
    }

    @Test
    fun mapTransferDataToTransferEntity() {
        val transferData = DataFactory.makeTransferData()
        val transferEntity = transferMapper.mapFromModel(transferData)

        assertEquals(transferData.id, transferEntity.id)
        assertEquals(transferData.clientId, transferEntity.clientId)
        assertEquals(transferData.data, transferEntity.data)
        assertEquals(transferData.token, transferEntity.token)
        assertEquals(transferData.value, transferEntity.value)
    }
}