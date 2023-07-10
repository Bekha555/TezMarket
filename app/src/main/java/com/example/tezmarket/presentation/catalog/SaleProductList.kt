package com.example.tezmarket.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tezmarket.R
import com.example.tezmarket.data.remote.model.prodouctsbycategory.Data
import com.example.tezmarket.navigation.Screen
import com.example.tezmarket.presentation.cart.CartViewModel
import com.example.tezmarket.presentation.favorites.FavoritesViewModel
import com.example.tezmarket.presentation.rating.RatingBar


@Composable
inline fun <reified T> SaleProductList(
    navController: NavController,
    product: T,
    favoritesViewModel: FavoritesViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {
    when (product) {
        is Data -> {
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 15.dp)
                    .clickable {
                        navController.navigate(
                            Screen.ProductDetailsScreen.passProductDetails(
                                product.id ?: 1
                            )
                        )
                    }
            ) {
                Row(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth()
                        .background(color = White, shape = RoundedCornerShape(15.dp)),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(110.dp)
                            .clip(RoundedCornerShape(bottomStart = 15.dp, topStart = 15.dp)),
                        model = product.image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                    Column(
                        modifier = Modifier.padding(
                            start = 10.dp,
                            top = 10.dp,
                            bottom = 10.dp,
                            end = 10.dp
                        )
                    ) {
                        Text(
                            text = product.name ?: "",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                            color = Black
                        )
                        Text(
                            text = "Mango",
                            fontSize = 11.sp,
                            fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                            color = Gray
                        )
                        RatingBar(
                            modifier = Modifier
                                .padding(top = 5.dp, bottom = 5.dp)
                                .size(12.dp),
                            startRating = product.avgRating!!.toInt(),
                            selectable = false,
                            ratingSelected = {},
                            text = product.avgRating.toString()
                        )
                        Text(
                            text = product.price.toString() + "TJS",
                            color = Black,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }
                }
                AnimatedButtons(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    isFavorite = product.isFavorite ?: false,
                    isCart = false,
                    favoriteToggle = {
                        favoritesViewModel.favoritesToggle(
                            product.id ?: 1,
                            productType = "product"
                        )
                    },
                    cartAdd = {
                        cartViewModel.addCartProduct(
                            product.id ?: 1,
                            productQuantity = 1
                        )
                    },
                    cartDel = { cartViewModel.delCartProduct(product.id ?: 1) })
            }
        }

        is com.example.tezmarket.data.remote.model.products.Data -> {
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 15.dp)
                    .clickable {
                        navController.navigate(
                            Screen.ProductDetailsScreen.passProductDetails(
                                product.id ?: 1
                            )
                        )
                    }
            ) {
                Row(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth()
                        .background(color = White, shape = RoundedCornerShape(15.dp)),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(110.dp)
                            .clip(RoundedCornerShape(bottomStart = 15.dp, topStart = 15.dp)),
                        model = product.image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                    Column(
                        modifier = Modifier.padding(
                            start = 10.dp,
                            top = 10.dp,
                            bottom = 10.dp,
                            end = 10.dp
                        )
                    ) {
                        Text(
                            text = product.name ?: "",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                            color = Black
                        )
                        Text(
                            text = "Mango",
                            fontSize = 11.sp,
                            fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                            color = Gray
                        )
                        RatingBar(
                            modifier = Modifier
                                .padding(top = 5.dp, bottom = 5.dp)
                                .size(12.dp),
                            startRating = product.avgRating!!.toInt(),
                            selectable = false,
                            ratingSelected = {},
                            text = product.avgRating.toString()
                        )
                        Text(
                            text = product.price.toString() + "TJS",
                            color = Black,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }
                }
                AnimatedButtons(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    isFavorite = product.isFavorite ?: false,
                    isCart = false,
                    favoriteToggle = {
                        favoritesViewModel.favoritesToggle(
                            product.id ?: 1,
                            productType = "product"
                        )
                    },
                    cartAdd = {
                        cartViewModel.addCartProduct(
                            product.id ?: 1,
                            productQuantity = 1
                        )
                    },
                    cartDel = { cartViewModel.delCartProduct(product.id ?: 1) })
            }
        }

        is com.example.tezmarket.data.remote.model.discProducts.Data -> {
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 15.dp)
                    .clickable {
                        navController.navigate(
                            Screen.ProductDetailsScreen.passProductDetails(
                                product.id ?: 1
                            )
                        )
                    }
            ) {
                Row(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth()
                        .background(color = White, shape = RoundedCornerShape(15.dp)),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(110.dp)
                            .clip(RoundedCornerShape(bottomStart = 15.dp, topStart = 15.dp)),
                        model = product.image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                    Column(
                        modifier = Modifier.padding(
                            start = 10.dp,
                            top = 10.dp,
                            bottom = 10.dp,
                            end = 10.dp
                        )
                    ) {
                        Text(
                            text = product.name ?: "",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                            color = Black
                        )
                        Text(
                            text = "Mango",
                            fontSize = 11.sp,
                            fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                            color = Gray
                        )
                        RatingBar(
                            modifier = Modifier
                                .padding(top = 5.dp, bottom = 5.dp)
                                .size(12.dp),
                            startRating = product.avgRating!!.toInt(),
                            selectable = false,
                            ratingSelected = {},
                            text = product.avgRating.toString()
                        )
                        Text(
                            text = product.price.toString() + "TJS",
                            color = Black,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }
                }
                AnimatedButtons(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    isFavorite = product.isFavorite ?: false,
                    isCart = false,
                    favoriteToggle = {
                        favoritesViewModel.favoritesToggle(
                            product.id ?: 1,
                            productType = "product"
                        )
                    },
                    cartAdd = {
                        cartViewModel.addCartProduct(
                            product.id ?: 1,
                            productQuantity = 1
                        )
                    },
                    cartDel = { cartViewModel.delCartProduct(product.id ?: 1) })
            }
        }
    }
}