package com.boni.data

class TokenManager {
    private var token: String = ""

    fun saveToken(token: String) {
        this.token = token
    }

    fun retrieveToken() = token
}