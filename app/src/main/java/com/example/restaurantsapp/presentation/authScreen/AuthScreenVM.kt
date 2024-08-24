package com.example.restaurantsapp.presentation.authScreen

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantsapp.data.local.entity.UserEntity
import com.example.restaurantsapp.domain.repository.AppRepository
import com.example.restaurantsapp.utils.Constants
import kotlinx.coroutines.launch

class AuthScreenVM(private val appRepository: AppRepository): ViewModel() {

    fun handleLogin(
        context: Context,
        mobileNumber: String,
        pin: String,
        navigate: () -> Unit
    ) {
        viewModelScope.launch {
            val user = appRepository.getUser(mobileNumber)

            when {
                user == null -> {
                    Toast.makeText(context, "User do not exists", Toast.LENGTH_SHORT).show()
                }

                user.pin != pin -> {
                    Toast.makeText(context, "Incorrect PIN", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    Constants.currentlyLoggedInUserNumber = user.mobileNumber
                    Constants.currentlyLoggedInUserName = user.name
                    navigate()
                }
            }
        }
    }

    fun registerUser(
        context: Context,
        name: String,
        mobileNumber: String,
        pin: String,
        navigate: () -> Unit
    ) {
        viewModelScope.launch {
            val user = appRepository.getUser(mobileNumber)

            if (user == null) {
                appRepository.register(UserEntity(mobileNumber, name, pin))
                navigate()
            } else {
                Toast.makeText(context, "User already registered", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun buttonEnabled(name: String, pin: String, screenType: String?): Boolean {
        return (pin.length == Constants.PIN_LENGTH) &&
                (name.trim().isNotEmpty() || screenType == ScreenType.LOGIN.name)
    }
}