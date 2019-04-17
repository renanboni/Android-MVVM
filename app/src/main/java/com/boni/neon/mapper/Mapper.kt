package com.boni.neon.mapper

interface Mapper<in D, out V> {
    fun mapToView(domain: D): V
}