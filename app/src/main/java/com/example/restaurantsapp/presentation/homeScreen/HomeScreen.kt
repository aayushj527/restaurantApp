package com.example.restaurantsapp.presentation.homeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.restaurantsapp.presentation.commonViews.RestaurantView

@Composable
fun HomeScreen(viewModel: HomeScreenVM) {
    val context = LocalContext.current
    val restaurants = viewModel.restaurantState.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn {
            items(restaurants.itemCount) { index ->
                restaurants[index]?.let { restaurant ->
                    RestaurantView(
                        index = index,
                        restaurant = restaurant,
                        thumbDownClicked = {
                            restaurant.fsqId?.let { fsqId ->
                                viewModel.rejectRestaurant(context, fsqId)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            restaurants.apply {
                when {
                    (loadState.refresh is LoadState.Loading) ||
                    (loadState.append is LoadState.Loading) -> {
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}