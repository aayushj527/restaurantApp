package com.example.restaurantsapp.utils

import androidx.core.text.isDigitsOnly

object Utility {
    fun mobileNumberValid(mobileNumber: String): Boolean {
        return (mobileNumber.length <= Constants.MOBILE_NUMBER_LENGTH && mobileNumber.isDigitsOnly())
    }

    fun pinValid(pin: String): Boolean {
        return (pin.length <= Constants.PIN_LENGTH && pin.isDigitsOnly())
    }
}