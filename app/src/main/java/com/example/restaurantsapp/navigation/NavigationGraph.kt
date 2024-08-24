package com.example.restaurantsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.restaurantsapp.presentation.authScreen.AuthScreen
import com.example.restaurantsapp.presentation.authScreen.AuthScreenVM
import com.example.restaurantsapp.presentation.homeScreen.HomeScreen
import com.example.restaurantsapp.presentation.homeScreen.HomeScreenVM
import com.example.restaurantsapp.presentation.landingScreen.LandingScreen
import org.koin.java.KoinJavaComponent.get

@Composable
fun RestaurantsNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LANDING_SCREEN
    ) {
        composable(Routes.LANDING_SCREEN) {
            LandingScreen { route ->
                navController.navigate(route)
            }
        }

        composable(Routes.AUTH_SCREEN) {
            val viewModel: AuthScreenVM = get(AuthScreenVM::class.java)

            AuthScreen(
                viewModel = viewModel,
                screenType = it.arguments?.getString("screenType")
            ) { route, popCurrentScreen ->
                navController.navigate(route) {
                    if (popCurrentScreen) {
                        popUpTo(0)
                    }
                }
            }
        }

        composable(Routes.HOME_SCREEN) {
            val viewModel: HomeScreenVM = get(HomeScreenVM::class.java)
            HomeScreen(viewModel = viewModel)
        }
    }
}