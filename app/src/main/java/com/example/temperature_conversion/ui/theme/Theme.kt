package com.example.temperature_conversion.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.navigation.NavController


private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

@Composable
fun Converter_appTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

@Composable
fun ColumnScope.ActionButton(text: String, onClick: () -> Unit, navController: NavController) {
    val focusManager = LocalFocusManager.current
    Button(
        onClick = { focusManager.clearFocus(); onClick()},
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(14.dp)
    ) {
        Text(text, fontSize = 15.sp)
    }
}

@Composable
fun CustomTextField(state: ValueState, imeAction: ImeAction, onValueChange: (String) -> Unit) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = state.value,
        isError = state.error != null,
        supportingText = { state.error?.let { Text(it) } },
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal, imeAction = imeAction),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        }),
        modifier = Modifier.width(125.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(updateMode: (ConverterViewModel.Mode) -> Unit) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    var temperature by remember {
        mutableStateOf("°C")
    }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it },
        modifier = Modifier.width(90.dp)
    ) {
        TextField(
            value = temperature,
            onValueChange = {},
            readOnly = true,
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor(),
            trailingIcon = { Icon(Icons.Filled.ArrowDropDown,
                contentDescription = "Arrow dropdown") }
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(text = "°C") },
                onClick = {
                    updateMode(ConverterViewModel.Mode.C)
                    temperature = "°C"
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = { Text(text = "°F") },
                onClick = {
                    updateMode(ConverterViewModel.Mode.F)
                    temperature = "°F"
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = { Text(text = "°K") },
                onClick = {
                    updateMode(ConverterViewModel.Mode.K)
                    temperature = "°K"
                    isExpanded = false
                }
            )
        }
    }
}

