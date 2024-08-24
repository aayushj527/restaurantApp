package com.example.restaurantsapp.presentation.commonViews

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
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
    reviews: HashMap<String, String>?,
    thumbDownClicked: () -> Unit,
    submitReview: (HashMap<String, String>) -> Unit
) {
    var reviewSectionExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    var review by rememberSaveable {
        mutableStateOf("")
    }

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

            Row(
                modifier = Modifier.padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Reviews",
                    fontWeight = FontWeight.SemiBold
                )

                IconButton(
                    onClick = { reviewSectionExpanded = !reviewSectionExpanded }
                ) {
                    Icon(
                        modifier = Modifier.rotate(if (reviewSectionExpanded) 180f else 0f),
                        painter = painterResource(id = R.drawable.ic_arrow),
                        contentDescription = "arrow"
                    )
                }
            }

            AnimatedVisibility(visible = reviewSectionExpanded) {
                Column {
                    reviews?.forEach {
                        ReviewView(
                            name = it.key,
                            review = it.value
                        )
                    }

                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        label = { Text(text = "Write your review") },
                        value = review,
                        onValueChange = { review = it },
                        trailingIcon = {
                            Text(
                                modifier = Modifier.clickable {
                                    reviews?.let {
                                        submitReview(reviews.apply { put(Constants.currentlyLoggedInUserName, review) })
                                    } ?: run {
                                        submitReview(hashMapOf(Constants.currentlyLoggedInUserName to review))
                                    }
                                },
                                text = "SUBMIT"
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ReviewView(
    name: String,
    review: String
) {
    Column(modifier = Modifier.padding(top = 16.dp)) {
        Text(
            text = name,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = review
        )
    }
}