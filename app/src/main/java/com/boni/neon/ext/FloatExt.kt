package com.boni.neon.ext

import java.text.NumberFormat
import java.util.*

fun Float.toMonetary(): String {
    return NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this / 100)
}