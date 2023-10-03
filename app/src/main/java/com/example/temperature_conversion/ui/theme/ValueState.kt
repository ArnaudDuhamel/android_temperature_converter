package com.example.temperature_conversion.ui.theme

data class ValueState (
    val value: String = "",
    val error: String? = null
) {
    fun toNumber() = value.toDoubleOrNull()
}