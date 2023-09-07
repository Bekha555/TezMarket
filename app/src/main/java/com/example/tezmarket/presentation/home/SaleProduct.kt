package com.example.tezmarket.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.tezmarket.R
import com.example.tezmarket.data.remote.model.products.Data
import com.example.tezmarket.presentation.cart.CartViewModel
import com.example.tezmarket.presentation.favorites.FavoritesViewModel
import com.example.tezmarket.presentation.rating.RatingBar
import com.example.tezmarket.ui.theme.Gray
import com.example.tezmarket.ui.theme.Primary

@Composable
fun <T> SaleProduct(
    sale_label: String,
    width: Dp,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    favoritesViewModel: FavoritesViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel(),
    product: T
) {


    when (product) {
        is Data -> {
            var selected by remember {
                mutableStateOf(if (product.isFavorite == true) 1 else 0)
            }
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                    .clickable(onClick = onClick)
            ) {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                )
                {
                    Box(
                        modifier = Modifier
                            .height(184.dp)
                            .width(width)
                    ) {
                        AsyncImage(
                            model = product.image,
                            contentDescription = "sale",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(184.dp)
                                .clip(shape = RoundedCornerShape(15.dp))
                        )

                        if (product.withDiscount == true) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .padding(start = 8.dp, top = 8.dp)
                                    .background(
                                        shape = RoundedCornerShape(25.dp),
                                        color = if (sale_label.contains("NEW") or sale_label.contains(
                                                "New"
                                            )
                                        ) {
                                            Black
                                        } else {
                                            Primary
                                        }
                                    )
                                    .align(Alignment.TopStart)
                            ) {
                                Text(
                                    text = product.percentageDiscount.toString() + "%",
                                    fontSize = 11.sp,
                                    color = White,
                                    modifier = Modifier
                                        .padding(vertical = 5.dp, horizontal = 10.dp)
                                )
                            }
                        }
                    }
                    AnimatedButtons(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        product.isFavorite ?: false,
                        false,
                        favoriteToggle = {
                            favoritesViewModel.favoritesToggle(
                                product.id ?: 1,
                                productType = "product"
                            )
                        },
                        cartAdd = {
                            cartViewModel.addCartProduct(product.id ?: 1, 1)
                        },
                        cartDel = {
                            cartViewModel.delCartProduct(product.id ?: 1)
                        }
                    )

                    Box(
                        modifier = Modifier
                            .height(14.dp)
                            .align(Alignment.BottomStart)
                    ) {
                        product.avgRating?.let {
                            RatingBar(
                                startRating = it,
                                selectable = false,
                                ratingSelected = {},
                                text = product.avgRating.toString(),
                                modifier = Modifier
                                    .size(12.dp)
                            )
                        }
                    }
                }
                Text(
                    text = product.category.name.toString(),
                    color = Gray,
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_regular))
                )
                Text(
                    text = product.name.toString(),
                    maxLines = 2,
                    color = Black,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                    modifier = Modifier.width(width)
                )
                Row {
                    if (product.withDiscount == true) {
                        Text(
                            text = product.price.toString() + "TJS",  // Без скидки
                            style = TextStyle(textDecoration = TextDecoration.LineThrough),
                            color = Gray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                    Text(
                        text = if (product.withDiscount == true) {
                            product.discountedPrice.toString() + "TJS"
                        } else {
                            product.price.toString() + "TJS"
                        },                                               // Со скидкой
                        color = if (product.withDiscount == true) {
                            Primary
                        } else {
                            Gray
                        },
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 3.dp)
                    )
                }
            }
        }

        is com.example.tezmarket.data.remote.model.recproducts.Data -> {
            var selected by remember {
                mutableStateOf(if (product.isFavorite == true) 1 else 0)
            }
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                    .clickable(onClick = onClick)

            ) {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                )
                {
                    Box(
                        modifier = Modifier
                            .height(184.dp)
                            .width(width)
                    ) {
                        AsyncImage(
                            model = product.image,
                            contentDescription = "sale",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(184.dp)
                                .clip(shape = RoundedCornerShape(15.dp))
                        )

                        if (product.withDiscount == true) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .padding(start = 8.dp, top = 8.dp)
                                    .background(
                                        shape = RoundedCornerShape(25.dp),
                                        color = if (sale_label.contains("NEW") or sale_label.contains(
                                                "New"
                                            )
                                        ) {
                                            Black
                                        } else {
                                            Primary
                                        }
                                    )
                                    .align(Alignment.TopStart)
                            ) {
                                Text(
                                    text = product.percentageDiscount.toString() + "%",
                                    fontSize = 11.sp,
                                    color = White,
                                    modifier = Modifier
                                        .padding(vertical = 5.dp, horizontal = 10.dp)
                                )
                            }
                        }
                    }
                    AnimatedButtons(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        product.isFavorite ?: false,
                        false,
                        favoriteToggle = {
                            favoritesViewModel.favoritesToggle(
                                product.id ?: 1,
                                productType = "product"
                            )
                        },
                        cartAdd = {
                            cartViewModel.addCartProduct(product.id ?: 1, 1)
                        },
                        cartDel = {
                            cartViewModel.delCartProduct(product.id ?: 1)
                        }
                    )

                    Box(
                        modifier = Modifier
                            .height(14.dp)
                            .align(Alignment.BottomStart)
                    ) {
                        product.avgRating?.let {
                            RatingBar(
                                startRating = it,
                                selectable = false,
                                ratingSelected = {},
                                text = product.avgRating.toString(),
                                modifier = Modifier
                                    .size(12.dp)
                            )
                        }
                    }
                }
                Text(
                    text = product.category.name.toString(),
                    color = Gray,
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_regular))
                )
                Text(
                    text = product.name.toString(),
                    maxLines = 2,
                    color = Black,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                    modifier = Modifier.width(width)
                )
                Row {
                    if (product.withDiscount == true) {
                        Text(
                            text = product.price.toString() + "TJS",  // Без скидки
                            style = TextStyle(textDecoration = TextDecoration.LineThrough),
                            color = Gray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                    Text(
                        text = if (product.withDiscount == true) {
                            product.discountedPrice.toString() + "TJS"
                        } else {
                            product.price.toString() + "TJS"
                        },                                               // Со скидкой
                        color = if (product.withDiscount == true) {
                            Primary
                        } else {
                            Gray
                        },
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 3.dp)
                    )
                }
            }
        }

        is com.example.tezmarket.data.remote.model.advertisements.Data -> {
            var selected by remember {
                mutableStateOf(if (product.isFavorite == true) 1 else 0)
            }
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                    .clickable(onClick = onClick)

            ) {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                )
                {
                    Box(
                        modifier = Modifier
                            .height(184.dp)
                            .width(width)
                    ) {
                        AsyncImage(
                            model = product.image,
                            contentDescription = "sale",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(184.dp)
                                .clip(shape = RoundedCornerShape(15.dp))
                        )
                    }
                    AnimatedButtons(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        product.isFavorite ?: false,
                        false,
                        favoriteToggle = {
                            favoritesViewModel.favoritesToggle(
                                product.id ?: 1,
                                productType = "product"
                            )
                        },
                        cartAdd = {
                            cartViewModel.addCartProduct(product.id ?: 1, 1)
                        },
                        cartDel = {
                            cartViewModel.delCartProduct(product.id ?: 1)
                        }
                    )

                    Box(
                        modifier = Modifier
                            .height(14.dp)
                            .align(Alignment.BottomStart)
                    ) {
                        product.avgRating?.let {
                            RatingBar(
                                startRating = it,
                                selectable = false,
                                ratingSelected = {},
                                text = product.avgRating.toString(),
                                modifier = Modifier
                                    .size(12.dp)
                            )
                        }
                    }
                }
                Text(
                    text = product.category?.name.toString(),
                    color = Gray,
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_regular))
                )
                Text(
                    text = product.name.toString(),
                    maxLines = 2,
                    color = Black,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                    modifier = Modifier.width(width)
                )

                Text(
                    text = product.price.toString() + "TJS",  // Без скидки
                    style = TextStyle(textDecoration = TextDecoration.LineThrough),
                    color = Gray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
        }


        is com.example.tezmarket.data.remote.model.simularproducts.Data -> {
            var selected by remember {
                mutableStateOf(if (product.isFavorite == true) 1 else 0)
            }
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                    .clickable(onClick = onClick)

            ) {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                )
                {
                    Box(
                        modifier = Modifier
                            .height(184.dp)
                            .width(width)
                    ) {
                        AsyncImage(
                            model = product.image,
                            contentDescription = "sale",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(184.dp)
                                .clip(shape = RoundedCornerShape(15.dp))
                        )

                        if (product.withDiscount == true) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .padding(start = 8.dp, top = 8.dp)
                                    .background(
                                        shape = RoundedCornerShape(25.dp),
                                        color = if (sale_label.contains("NEW") or sale_label.contains(
                                                "New"
                                            )
                                        ) {
                                            Black
                                        } else {
                                            Primary
                                        }
                                    )
                                    .align(Alignment.TopStart)
                            ) {
                                Text(
                                    text = product.percentageDiscount.toString() + "%",
                                    fontSize = 11.sp,
                                    color = White,
                                    modifier = Modifier
                                        .padding(vertical = 5.dp, horizontal = 10.dp)
                                )
                            }
                        }
                    }
                    AnimatedButtons(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        product.isFavorite ?: false,
                        false,
                        favoriteToggle = {
                            favoritesViewModel.favoritesToggle(
                                product.id ?: 1,
                                productType = "product"
                            )
                        },
                        cartAdd = {
                            cartViewModel.addCartProduct(product.id ?: 1, 1)
                        },
                        cartDel = {
                            cartViewModel.delCartProduct(product.id ?: 1)
                        }
                    )

                    Box(
                        modifier = Modifier
                            .height(14.dp)
                            .align(Alignment.BottomStart)
                    ) {
                        product.avgRating?.let {
                            RatingBar(
                                startRating = it,
                                selectable = false,
                                ratingSelected = {},
                                text = product.avgRating.toString(),
                                modifier = Modifier
                                    .size(12.dp)
                            )
                        }
                    }
                }
                Text(
                    text = product.category?.name.toString(),
                    color = Gray,
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_regular))
                )
                Text(
                    text = product.name.toString(),
                    maxLines = 2,
                    color = Black,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                    modifier = Modifier.width(width)
                )
                Row {
                    if (product.withDiscount == true) {
                        Text(
                            text = product.price.toString() + "TJS",  // Без скидки
                            style = TextStyle(textDecoration = TextDecoration.LineThrough),
                            color = Gray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                    Text(
                        text = if (product.withDiscount == true) {
                            product.discountedPrice.toString() + "TJS"
                        } else {
                            product.price.toString() + "TJS"
                        },                                               // Со скидкой
                        color = if (product.withDiscount == true) {
                            Primary
                        } else {
                            Gray
                        },
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 3.dp)
                    )
                }
            }
        }

        is com.example.tezmarket.data.remote.model.discProducts.Data -> {
            var selected by remember {
                mutableStateOf(if (product.isFavorite == true) 1 else 0)
            }
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                    .clickable(onClick = onClick)

            ) {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                )
                {
                    Box(
                        modifier = Modifier
                            .height(184.dp)
                            .width(width)
                    ) {
                        AsyncImage(
                            model = product.image,
                            contentDescription = "sale",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(184.dp)
                                .clip(shape = RoundedCornerShape(15.dp))
                        )

                        if (product.withDiscount == true) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .padding(start = 8.dp, top = 8.dp)
                                    .background(
                                        shape = RoundedCornerShape(25.dp),
                                        color = if (sale_label.contains("NEW") or sale_label.contains(
                                                "New"
                                            )
                                        ) {
                                            Black
                                        } else {
                                            Primary
                                        }
                                    )
                                    .align(Alignment.TopStart)
                            ) {
                                Text(
                                    text = product.percentageDiscount.toString() + "%",
                                    fontSize = 11.sp,
                                    color = White,
                                    modifier = Modifier
                                        .padding(vertical = 5.dp, horizontal = 10.dp)
                                )
                            }
                        }
                    }
                    AnimatedButtons(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        product.isFavorite ?: false,
                        false,
                        favoriteToggle = {
                            favoritesViewModel.favoritesToggle(
                                product.id ?: 1,
                                productType = "product"
                            )
                        },
                        cartAdd = {
                            cartViewModel.addCartProduct(product.id ?: 1, 1)
                        },
                        cartDel = {
                            cartViewModel.delCartProduct(product.id ?: 1)
                        }
                    )

                    Box(
                        modifier = Modifier
                            .height(14.dp)
                            .align(Alignment.BottomStart)
                    ) {
                        product.avgRating?.let {
                            RatingBar(
                                startRating = it,
                                selectable = false,
                                ratingSelected = {},
                                text = product.avgRating.toString(),
                                modifier = Modifier
                                    .size(12.dp)
                            )
                        }
                    }
                }
                Text(
                    text = product.category?.name.toString(),
                    color = Gray,
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_regular))
                )
                Text(
                    text = product.name.toString(),
                    maxLines = 2,
                    color = Black,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                    modifier = Modifier.width(width)
                )
                Row {
                    if (product.withDiscount == true) {
                        Text(
                            text = product.price.toString() + "TJS",  // Без скидки
                            style = TextStyle(textDecoration = TextDecoration.LineThrough),
                            color = Gray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                    Text(
                        text = if (product.withDiscount == true) {
                            product.discountedPrice.toString() + "TJS"
                        } else {
                            product.price.toString() + "TJS"
                        },                                               // Со скидкой
                        color = if (product.withDiscount == true) {
                            Primary
                        } else {
                            Gray
                        },
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 3.dp)
                    )
                }
            }
        }

        is com.example.tezmarket.data.remote.model.shops.Data -> {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                    .clickable(onClick = onClick)

            ) {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                )
                {
                    Box(
                        modifier = Modifier
                            .height(184.dp)
                            .width(width)
                    ) {
                        AsyncImage(
                            model = product.logo,
                            contentDescription = "sale",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .height(184.dp)
                                .clip(shape = RoundedCornerShape(15.dp))
                        )
                    }
                }
                Text(
                    text = product.address.toString(),
                    color = Gray,
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_regular))
                )
                Text(
                    text = product.name.toString(),
                    maxLines = 2,
                    color = Black,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                    modifier = Modifier.width(width)
                )
            }
        }
        is com.example.tezmarket.data.remote.model.prodouctsbycategory.Data -> {
            var selected by remember {
                mutableStateOf(if (product.isFavorite == true) 1 else 0)
            }
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                    .clickable(onClick = onClick)

            ) {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                )
                {
                    Box(
                        modifier = Modifier
                            .height(184.dp)
                            .width(width)
                    ) {
                        AsyncImage(
                            model = product.image,
                            contentDescription = "sale",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(184.dp)
                                .clip(shape = RoundedCornerShape(15.dp))
                        )

                        if (product.withDiscount == true) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .padding(start = 8.dp, top = 8.dp)
                                    .background(
                                        shape = RoundedCornerShape(25.dp),
                                        color = if (sale_label.contains("NEW") or sale_label.contains(
                                                "New"
                                            )
                                        ) {
                                            Black
                                        } else {
                                            Primary
                                        }
                                    )
                                    .align(Alignment.TopStart)
                            ) {
                                Text(
                                    text = product.percentageDiscount.toString() + "%",
                                    fontSize = 11.sp,
                                    color = White,
                                    modifier = Modifier
                                        .padding(vertical = 5.dp, horizontal = 10.dp)
                                )
                            }
                        }
                    }
                    AnimatedButtons(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        product.isFavorite ?: false,
                        false,
                        favoriteToggle = {
                            favoritesViewModel.favoritesToggle(
                                product.id ?: 1,
                                productType = "product"
                            )
                        },
                        cartAdd = {
                            cartViewModel.addCartProduct(product.id ?: 1, 1)
                        },
                        cartDel = {
                            cartViewModel.delCartProduct(product.id ?: 1)
                        }
                    )

                    Box(
                        modifier = Modifier
                            .height(14.dp)
                            .align(Alignment.BottomStart)
                    ) {
                        product.avgRating?.let {
                            RatingBar(
                                startRating = it,
                                selectable = false,
                                ratingSelected = {},
                                text = product.avgRating.toString(),
                                modifier = Modifier
                                    .size(12.dp)
                            )
                        }
                    }
                }
                Text(
                    text = product.category?.name.toString(),
                    color = Gray,
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_regular))
                )
                Text(
                    text = product.name.toString(),
                    maxLines = 2,
                    color = Black,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                    modifier = Modifier.width(width)
                )
                Row {
                    if (product.withDiscount == true) {
                        Text(
                            text = product.price.toString() + "TJS",  // Без скидки
                            style = TextStyle(textDecoration = TextDecoration.LineThrough),
                            color = Gray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                    Text(
                        text = if (product.withDiscount == true) {
                            product.discountedPrice.toString() + "TJS"
                        } else {
                            product.price.toString() + "TJS"
                        },                                               // Со скидкой
                        color = if (product.withDiscount == true) {
                            Primary
                        } else {
                            Gray
                        },
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 3.dp)
                    )
                }
            }
        }
        is com.example.tezmarket.data.remote.model.filteredata.Data -> {
            var selected by remember {
                mutableStateOf(if (product.isFavorite == true) 1 else 0)
            }
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                    .clickable(onClick = onClick)

            ) {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                )
                {
                    Box(
                        modifier = Modifier
                            .height(184.dp)
                            .width(width)
                    ) {
                        AsyncImage(
                            model = product.image,
                            contentDescription = "sale",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(184.dp)
                                .clip(shape = RoundedCornerShape(15.dp))
                        )

                        if (product.withDiscount == true) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .padding(start = 8.dp, top = 8.dp)
                                    .background(
                                        shape = RoundedCornerShape(25.dp),
                                        color = if (sale_label.contains("NEW") or sale_label.contains(
                                                "New"
                                            )
                                        ) {
                                            Black
                                        } else {
                                            Primary
                                        }
                                    )
                                    .align(Alignment.TopStart)
                            ) {
                                Text(
                                    text = product.percentageDiscount.toString() + "%",
                                    fontSize = 11.sp,
                                    color = White,
                                    modifier = Modifier
                                        .padding(vertical = 5.dp, horizontal = 10.dp)
                                )
                            }
                        }
                    }
                    AnimatedButtons(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        product.isFavorite ?: false,
                        false,
                        favoriteToggle = {
                            favoritesViewModel.favoritesToggle(
                                product.id ?: 1,
                                productType = "product"
                            )
                        },
                        cartAdd = {
                            cartViewModel.addCartProduct(product.id ?: 1, 1)
                        },
                        cartDel = {
                            cartViewModel.delCartProduct(product.id ?: 1)
                        }
                    )

                    Box(
                        modifier = Modifier
                            .height(14.dp)
                            .align(Alignment.BottomStart)
                    ) {
                        product.avgRating?.let {
                            RatingBar(
                                startRating = it,
                                selectable = false,
                                ratingSelected = {},
                                text = product.avgRating.toString(),
                                modifier = Modifier
                                    .size(12.dp)
                            )
                        }
                    }
                }
                Text(
                    text = product.category?.name.toString(),
                    color = Gray,
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_regular))
                )
                Text(
                    text = product.name.toString(),
                    maxLines = 2,
                    color = Black,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                    modifier = Modifier.width(width)
                )
                Row {
                    if (product.withDiscount == true) {
                        Text(
                            text = product.price.toString() + "TJS",  // Без скидки
                            style = TextStyle(textDecoration = TextDecoration.LineThrough),
                            color = Gray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                    Text(
                        text = if (product.withDiscount == true) {
                            product.discountedPrice.toString() + "TJS"
                        } else {
                            product.price.toString() + "TJS"
                        },                                               // Со скидкой
                        color = if (product.withDiscount == true) {
                            Primary
                        } else {
                            Gray
                        },
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 3.dp)
                    )
                }
            }
        }
        is com.example.tezmarket.data.remote.model.shopProducts.Data -> {
            var selected by remember {
                mutableStateOf(if (product.isFavorite == true) 1 else 0)
            }
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                    .clickable(onClick = onClick)

            ) {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                )
                {
                    Box(
                        modifier = Modifier
                            .height(184.dp)
                            .width(width)
                    ) {
                        AsyncImage(
                            model = product.image,
                            contentDescription = "sale",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(184.dp)
                                .clip(shape = RoundedCornerShape(15.dp))
                        )

                        if (product.withDiscount == true) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .padding(start = 8.dp, top = 8.dp)
                                    .background(
                                        shape = RoundedCornerShape(25.dp),
                                        color = if (sale_label.contains("NEW") or sale_label.contains(
                                                "New"
                                            )
                                        ) {
                                            Black
                                        } else {
                                            Primary
                                        }
                                    )
                                    .align(Alignment.TopStart)
                            ) {
                                Text(
                                    text = product.percentageDiscount.toString() + "%",
                                    fontSize = 11.sp,
                                    color = White,
                                    modifier = Modifier
                                        .padding(vertical = 5.dp, horizontal = 10.dp)
                                )
                            }
                        }
                    }
                    AnimatedButtons(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        product.isFavorite ?: false,
                        false,
                        favoriteToggle = {
                            favoritesViewModel.favoritesToggle(
                                product.id ?: 1,
                                productType = "product"
                            )
                        },
                        cartAdd = {
                            cartViewModel.addCartProduct(product.id ?: 1, 1)
                        },
                        cartDel = {
                            cartViewModel.delCartProduct(product.id ?: 1)
                        }
                    )

                    Box(
                        modifier = Modifier
                            .height(14.dp)
                            .align(Alignment.BottomStart)
                    ) {
                        product.avgRating?.let {
                            RatingBar(
                                startRating = it,
                                selectable = false,
                                ratingSelected = {},
                                text = product.avgRating.toString(),
                                modifier = Modifier
                                    .size(12.dp)
                            )
                        }
                    }
                }
                Text(
                    text = product.category?.name.toString(),
                    color = Gray,
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_regular))
                )
                Text(
                    text = product.name.toString(),
                    maxLines = 2,
                    color = Black,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                    modifier = Modifier.width(width)
                )
                Row {
                    if (product.withDiscount == true) {
                        Text(
                            text = product.price.toString() + "TJS",  // Без скидки
                            style = TextStyle(textDecoration = TextDecoration.LineThrough),
                            color = Gray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                    Text(
                        text = if (product.withDiscount == true) {
                            product.discountedPrice.toString() + "TJS"
                        } else {
                            product.price.toString() + "TJS"
                        },                                               // Со скидкой
                        color = if (product.withDiscount == true) {
                            Primary
                        } else {
                            Gray
                        },
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 3.dp)
                    )
                }
            }
        }
        is com.example.tezmarket.data.remote.model.otherAdvertisements.Data -> {
            var selected by remember {
                mutableStateOf(if (product.isFavorite == true) 1 else 0)
            }
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                    .clickable(onClick = onClick)

            ) {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                )
                {
                    Box(
                        modifier = Modifier
                            .height(184.dp)
                            .width(width)
                    ) {
                        AsyncImage(
                            model = product.image,
                            contentDescription = "sale",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(184.dp)
                                .clip(shape = RoundedCornerShape(15.dp))
                        )
                    }
                    AnimatedButtons(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        product.isFavorite ?: false,
                        false,
                        favoriteToggle = {
                            favoritesViewModel.favoritesToggle(
                                product.id ?: 1,
                                productType = "product"
                            )
                        },
                        cartAdd = {
                            cartViewModel.addCartProduct(product.id ?: 1, 1)
                        },
                        cartDel = {
                            cartViewModel.delCartProduct(product.id ?: 1)
                        }
                    )

                    Box(
                        modifier = Modifier
                            .height(14.dp)
                            .align(Alignment.BottomStart)
                    ) {
                        product.avgRating?.let {
                            RatingBar(
                                startRating = it,
                                selectable = false,
                                ratingSelected = {},
                                text = product.avgRating.toString(),
                                modifier = Modifier
                                    .size(12.dp)
                            )
                        }
                    }
                }
                Text(
                    text = product.category?.name.toString(),
                    color = Gray,
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_regular))
                )
                Text(
                    text = product.name.toString(),
                    maxLines = 2,
                    color = Black,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                    modifier = Modifier.width(width)
                )

                        Text(
                            text = product.price.toString() + "TJS",
                            style = TextStyle(textDecoration = TextDecoration.LineThrough),
                            color = Gray,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                        )
            }
        }
    }
}

