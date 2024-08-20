package com.example.restaurantsapp.navigation

import com.example.restaurantsapp.presentation.authScreen.ScreenType

fun getAuthScreenRoute(screenType: ScreenType): String {
    return "auth_screen?screenType=${screenType.name}"
}