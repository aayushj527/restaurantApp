package com.example.restaurantsapp.presentation.authScreen

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantsapp.domain.repository.AppRepository
import kotlinx.coroutines.launch

class AuthScreenVM(private val appRepository: AppRepository): ViewModel() {

    fun handleLogin(
        context: Context,
        mobileNumber: String,
        pin: String,
        navigate: (String) -> Unit
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
                    navigate("")
                }
            }
        }
    }
}