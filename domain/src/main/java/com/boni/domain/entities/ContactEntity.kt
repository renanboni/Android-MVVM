package com.boni.domain.entities

data class ContactEntity (
    val id: String,
    val name: String,
    val phone: String,
    val avatar: String,
    var value: Float = 0f
)