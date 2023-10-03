package com.example.temperature_conversion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.temperature_conversion.ui.theme.App
import com.example.temperature_conversion.ui.theme.Result
import androidx.navigation.compose.composable
import com.example.temperature_conversion.ui.theme.ConverterViewModel
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun NavGraph (navController: NavHostController, vm: ConverterViewModel = viewModel()){
    NavHost(
        navController = navController,
        startDestination = Screens.Front.route)
    {
        composable(route = Screens.Front.route){
            App(navController, vm)
        }
        composable(route = Screens.Result.route){
            Result(navController, vm)
        }
    }
}