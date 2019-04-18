package com.boni.data.mapper

import com.boni.data.entities.TransferData
import com.boni.domain.entities.TransferEntity

class TransferMapper: ModelMapper<TransferData, TransferEntity> {
    override fun mapFromModel(model: TransferData): TransferEntity {
        return TransferEntity(
            id = model.id,
            clientId = model.clientId,
            value = model.value,
            token = model.token,
            data = model.data
        )
    }
}