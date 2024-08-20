package com.example.restaurantsapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.restaurantsapp.presentation.authScreen.AuthScreen
import com.example.restaurantsapp.presentation.authScreen.AuthScreenVM
import com.example.restaurantsapp.presentation.authScreen.ScreenType
import com.example.restaurantsapp.presentation.landingScreen.LandingScreen
import org.koin.java.KoinJavaComponent.get
import org.koin.java.KoinJavaComponent.inject

@Composable
fun RestaurantsNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "landing_screen"
    ) {
        composable("landing_screen") {
            LandingScreen { route ->
                navController.navigate(route)
            }
        }

        composable("auth_screen?screenType={screenType}") {
            val viewModel: AuthScreenVM = get(AuthScreenVM::class.java)

            AuthScreen(
                viewModel = viewModel,
                screenType = it.arguments?.getString("screenType")
            ) { route ->
                navController.navigate(route)
            }
        }

        composable("home_screen") {

        }
    }
}