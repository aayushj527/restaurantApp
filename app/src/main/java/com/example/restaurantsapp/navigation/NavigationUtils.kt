package com.example.restaurantsapp.navigation

import com.example.restaurantsapp.presentation.authScreen.ScreenType

fun getAuthScreenRoute(screenType: ScreenType): String {
    return Routes.AUTH_SCREEN.replace("{screenType}", screenType.name)
}