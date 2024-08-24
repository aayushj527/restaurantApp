package com.example.restaurantsapp.data.remote.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RestaurantsResponse(
    val results: ArrayList<Restaurant>
): Parcelable

@Parcelize
data class Restaurant(
    @SerializedName("fsq_id") val fsqId: String? = null,
    val name: String? = null,
    val geocodes: GeoCodes? = GeoCodes(),
    val location: Location? = Location()
): Parcelable

@Parcelize
data class Location(
    @SerializedName("formatted_address") val formattedAddress: String? = null
): Parcelable

@Parcelize
data class GeoCodes(
    val main: Main? = Main()
): Parcelable

@Parcelize
data class Main(
    val latitude: Double? = null,
    val longitude: Double? = null
): Parcelable