package com.example.restaurantsapp.presentation.landingScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurantsapp.navigation.getAuthScreenRoute
import com.example.restaurantsapp.presentation.authScreen.ScreenType

@Composable
fun LandingScreen(
    navigate: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                navigate(getAuthScreenRoute(ScreenType.SIGN_UP))
            }
        ) {
            Text(text = "Register")
        }

        Button(
            modifier = Modifier.padding(top = 32.dp),
            onClick = {
                navigate(getAuthScreenRoute(ScreenType.LOGIN))
            }
        ) {
            Text(text = "Already registered? Login in")
        }
    }
}