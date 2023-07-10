package com.example.tezmarket.presentation.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tezmarket.R
import com.example.tezmarket.data.remote.model.favorites.Data
import com.example.tezmarket.navigation.Screen
import com.example.tezmarket.presentation.rating.RatingBar
import com.example.tezmarket.ui.theme.Gray
import com.example.tezmarket.ui.theme.Primary


@Composable
fun FavoriteSaleProduct(
    navController: NavController,
    favoriteProduct: Data,
    favoritesViewModel: FavoritesViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 15.dp)
            .clickable(onClick = {
                navController.navigate(
                    Screen.ProductDetailsScreen.passProductDetails(
                        favoriteProduct.id!!
                    )
                )
            })
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth()
                .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
                .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier.size(110.dp),
                model = favoriteProduct.image,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Column(
                modifier = Modifier.padding(
                    start = 10.dp,
                    top = 10.dp,
                    bottom = 10.dp,
                    end = 10.dp
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = favoriteProduct.category?.name.toString(),
                        fontSize = 11.sp,
                        color = Gray
                    )
                    IconButton(
                        modifier = Modifier.size(23.dp),
                        onClick = {
                            favoritesViewModel.favoritesToggle(
                                favoriteProduct.id ?: 1,
                                "product"
                            )
                            favoritesViewModel.getFavoriteProducts()
                        }) {
                        Image(
                            modifier = Modifier.size(12.dp),
                            contentScale = ContentScale.Crop,
                            painter = painterResource(id = R.drawable.favorites_card_clear_button),
                            contentDescription = null
                        )
                    }
                }
                Text(
                    text = favoriteProduct.name.toString(),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_bold))

                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(13.dp),
                ) {
                    Row {
                        Text(text = "Цвет:", fontSize = 11.sp, color = Gray)
                        Text(text = "Синий", fontSize = 11.sp)
                    }
                    Row {
                        Text(text = "Размер:", fontSize = 11.sp, color = Gray)
                        Text(text = "L", fontSize = 11.sp)
                    }
                }
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${favoriteProduct.price} TJS",
                        fontFamily = FontFamily(Font(R.font.metropolis_bold))
                    )
                    RatingBar(
                        startRating = favoriteProduct.avgRating!!.toInt(),
                        selectable = false,
                        ratingSelected = {},
                        text = "",
                        modifier = Modifier
                            .size(12.dp)
                    )
                }
            }

        }
        IconButton(
            onClick = {},
            modifier = Modifier
                .size(40.dp)
                .shadow(elevation = 4.dp, clip = true, shape = CircleShape)
                .clip(CircleShape)
                .background(Primary, shape = CircleShape)
                .align(Alignment.BottomEnd)
        ) {
            Image(
                painter = painterResource(R.drawable.activated),
                contentDescription = "heart",
                colorFilter = ColorFilter.tint(color = Color.White),
                modifier = Modifier
                    .size(19.dp)
            )
        }
    }
}

