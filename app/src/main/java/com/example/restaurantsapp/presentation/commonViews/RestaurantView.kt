package com.example.restaurantsapp.presentation.commonViews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.restaurantsapp.R
import com.example.restaurantsapp.data.remote.api.Restaurant
import com.example.restaurantsapp.utils.Constants
import com.example.restaurantsapp.utils.bounceClick

@Composable
fun RestaurantView(
    index: Int,
    restaurant: Restaurant,
    thumbDownClicked: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = Constants.IMAGE_URL,
                contentScale = ContentScale.Fit,
                contentDescription = "restaurant image"
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                restaurant.name?.let { name ->
                    Text(
                        text = "${index + 1}. $name",
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Icon(
                    modifier = Modifier.bounceClick {
                        thumbDownClicked()
                    },
                    painter = painterResource(id = R.drawable.ic_thumb_down),
                    contentDescription = "thumb down icon"
                )
            }

            restaurant.location?.formattedAddress?.let { formattedAddress ->
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = formattedAddress
                )
            }
        }
    }
}