package com.example.temperature_conversion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.temperature_conversion.navigation.NavGraph
import com.example.temperature_conversion.ui.theme.App
import com.example.temperature_conversion.ui.theme.Converter_appTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Converter_appTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Temp_Conversion_Preview() {
    Converter_appTheme {
        //App()
    }
}
