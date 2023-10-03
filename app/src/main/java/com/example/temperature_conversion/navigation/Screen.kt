package com.example.temperature_conversion.navigation

sealed class Screens(val route: String) {
    object Front: Screens("front_screen")
    object Result: Screens("result_screen")
}