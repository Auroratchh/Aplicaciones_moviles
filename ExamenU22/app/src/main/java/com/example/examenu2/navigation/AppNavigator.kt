package com.example.examenu2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examenu2.views.ContactFormScreen
import com.example.examenu2.views.MainScreen
import com.example.examenu2.views.SettingsScreen

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {

        composable("main") {
            MainScreen(navController = navController)
        }

        composable("settings") {
            SettingsScreen(navController = navController)
        }

        composable("contact_form") {
            ContactFormScreen(navController = navController)
        }
    }
}