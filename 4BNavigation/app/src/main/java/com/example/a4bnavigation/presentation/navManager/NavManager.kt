package com.example.a4bnavigation.presentation.navManager

import android.R.attr.description
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.a4bnavigation.presentation.views.DetailsView
import com.example.a4bnavigation.presentation.views.HomeView



@Composable
fun NavManager(navController: NavController, id: Long){
    val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = "Home"
    ){
        composable("Home") {
            HomeView(navController, id)
        }
        composable("Details/{id}", arguments = listOf(
            navArgument("id"){
                type = NavType.LongType
            }
        )) {
            val description = it.arguments?.getLong("id") ?: 0
            DetailsView(navController, id)
        }
    }
}