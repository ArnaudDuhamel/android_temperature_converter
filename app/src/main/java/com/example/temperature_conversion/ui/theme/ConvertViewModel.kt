package com.example.temperature_conversion.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ConverterViewModel : ViewModel() {
    private var temperatureCelsius by mutableStateOf(0.0)

    private var temperatureFarenheit by mutableStateOf(0.0)

    private var temperatureKelvin by mutableStateOf(0.0)

    private var selectedMode by mutableStateOf(Mode.C)

    private var inputState by mutableStateOf(ValueState(value = "", error = null))

    fun getCelsius(): String {
        return String.format("%.2f°C", temperatureCelsius)
    }

    fun getFarenheit(): String {
        return String.format("%.2f°F", temperatureFarenheit)
    }

    fun getKelvin(): String {
        return String.format("%.2f°K", temperatureKelvin)
    }

    fun getInputStateValue(): ValueState {
        return inputState
    }

    fun updateInput(it: String){
        inputState = inputState.copy(value = it, error = null)
    }

    fun calculate() {
        val temperature = inputState.toNumber()
        if (temperature == null)
            inputState = inputState.copy(error = "Invalid number")
        else convert(temperature, selectedMode)
    }

    private fun convert(temperature: Double, degree: Mode) {
        when (degree) {
            Mode.C -> {
                temperatureCelsius = temperature
                temperatureFarenheit = ( temperatureCelsius * 9 / 5 ) + 32
                temperatureKelvin = temperatureCelsius + 273.15
            }
            Mode.F -> {
                temperatureFarenheit = temperature
                temperatureCelsius = ( temperatureFarenheit - 32 ) * 5 / 9
                temperatureKelvin = temperatureCelsius + 273.15

            }
            Mode.K -> {
                temperatureKelvin = temperature
                temperatureCelsius = temperatureKelvin - 273.15
                temperatureFarenheit = ( temperatureCelsius * 9 / 5 ) + 32
            }
        }

    }

    fun updateMode(it: Mode) {
        selectedMode = it
    }

    fun reset(){
        selectedMode = Mode.C
        inputState = inputState.copy(value = "", error = null)
    }

    enum class Mode {C, F, K}
}
