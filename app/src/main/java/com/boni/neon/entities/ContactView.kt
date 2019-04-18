package com.boni.neon.entities

import java.io.Serializable

data class ContactView (
    val id: String,
    val name: String,
    val phone: String,
    val avatar: String
): Serializable