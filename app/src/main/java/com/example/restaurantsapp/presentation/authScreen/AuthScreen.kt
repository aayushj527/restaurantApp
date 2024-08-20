package com.example.restaurantsapp.presentation.authScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.restaurantsapp.utils.Constants
import com.example.restaurantsapp.utils.Utility

@Composable
fun AuthScreen(
    viewModel: AuthScreenVM,
    screenType: String?,
    navigate: (String) -> Unit
) {
    val context = LocalContext.current

    var mobileNumber by rememberSaveable {
        mutableStateOf("")
    }

    var pin by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Mobile number")
            },
            value = mobileNumber,
            onValueChange = {
                if (Utility.mobileNumberValid(it)) {
                    mobileNumber = it
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        TextField(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            label = {
                Text(
                    text = if (screenType == ScreenType.LOGIN.name) "Enter PIN" else "Set PIN"
                )
            },
            value = pin,
            onValueChange = {
                if (Utility.pinValid(it)) {
                    pin = it
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(
            modifier = Modifier.padding(top = 32.dp),
            enabled = pin.length == Constants.PIN_LENGTH,
            onClick = {
                if (screenType == ScreenType.LOGIN.name) {
                    viewModel.handleLogin(context, mobileNumber, pin, navigate)
                } else {
//                    viewModel.
                }
            }
        ) {
            Text(text = if (screenType == ScreenType.LOGIN.name) "Login" else "Sign Up")
        }
    }
}