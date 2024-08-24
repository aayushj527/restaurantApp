package com.example.restaurantsapp.presentation.homeScreen

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.restaurantsapp.data.local.entity.ReviewEntity
import com.example.restaurantsapp.data.remote.api.Restaurant
import com.example.restaurantsapp.domain.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class HomeScreenVM(private val appRepository: AppRepository) : ViewModel() {

    private val _restaurantState: MutableStateFlow<PagingData<Restaurant>> =
        MutableStateFlow(value = PagingData.empty())
    val restaurantState: MutableStateFlow<PagingData<Restaurant>>
        get() = _restaurantState

    val reviews = appRepository.getReviews().asLiveData()

    init {
        viewModelScope.launch {
            getRestaurants()
//            getReviews()
        }
    }

//    private suspend fun getReviews() {
//        appRepository.getReviews().collectLatest {
//            _reviewsState.tryEmit(it)
//        }
//    }

    fun submitReview(fsqId: String, reviews: HashMap<String, String>) {
        viewModelScope.launch {
            appRepository.updateReviews(fsqId, reviews)
        }
    }

    private suspend fun getRestaurants() {
        val rejectedRestaurants = appRepository.getRejectRestaurants()

        appRepository
            .getRestaurants()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect { restaurants ->
                _restaurantState.value = if (rejectedRestaurants.isNullOrEmpty()) {
                    restaurants
                } else {
                    restaurants.filter { restaurant ->
                        !rejectedRestaurants.contains(restaurant.fsqId)
                    }
                }
            }
    }

    fun rejectRestaurant(context: Context, fsqId: String) {
        viewModelScope.launch {
            appRepository.rejectRestaurant(fsqId)
            Toast.makeText(
                context,
                "Restaurant disliked and won't be shown again next time onwards.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}