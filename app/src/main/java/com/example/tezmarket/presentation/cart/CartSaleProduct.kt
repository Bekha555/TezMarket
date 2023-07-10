package com.example.tezmarket.presentation.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tezmarket.R
import com.example.tezmarket.data.remote.model.cart.Data
import com.example.tezmarket.navigation.Screen
import com.example.tezmarket.presentation.favorites.FavoritesViewModel
import com.example.tezmarket.ui.theme.Gray

@SuppressLint("UnrememberedMutableState")
@Composable
fun CartSaleProduct(
    navController: NavController,
    data: Data,
    cartViewModel: CartViewModel = hiltViewModel(),
    favoritesViewModel: FavoritesViewModel = hiltViewModel()
) {
    var showMenu by remember {
        mutableStateOf(false)
    }
    var count by remember {
        mutableStateOf(data.quantity)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 8.dp, bottom = 8.dp)
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
            .clickable(onClick = {
                navController.navigate(Screen.ProductDetailsScreen.passProductDetails(data.product?.id!!))
            })
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = White, shape = RoundedCornerShape(10.dp))
        ) {

            AsyncImage(
                model = data.product?.images?.first(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(105.dp)
            )

            Column(modifier = Modifier.padding(start = 10.dp, top = 10.dp)) {
                Text(
                    text = data.product?.name.toString(),
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                    color = Black
                )
                Row() {
                    Text(
                        text = "Цвет: ",
                        fontSize = 11.sp,
                        fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                        color = Color.Gray
                    )
                    Text(
                        text = "Серый",
                        fontSize = 11.sp,
                        fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                        color = Black
                    )
                    Text(
                        text = "Размер: ",
                        fontSize = 11.sp,
                        fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                    Text(
                        text = " L",
                        fontSize = 11.sp,
                        fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                        color = Black
                    )
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 45.dp, start = 115.dp)
                .width(110.dp)
                .align(Alignment.CenterStart)

        ) {
            IconButton(
                onClick = {
                    if (count!! >= 1) {
                        cartViewModel.modCartProduct(
                            productId = data.id!!,
                            productQuantity = count!! - 1,
                            cartId = data.id!!
                        )
                        count = count!! - 1
                    }
                },
                modifier = Modifier
                    .size(36.dp)
                    .shadow(elevation = 6.dp, clip = true, shape = CircleShape)
                    .clip(CircleShape)
                    .background(White)

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.minus_icon),
                    contentDescription = "Minus", tint = Gray, modifier = Modifier.size(10.dp)
                )
            }
            Text(
                text = count.toString(),
                fontSize = 14.sp,
                color = Black,
            )
            IconButton(
                onClick = {
                    cartViewModel.modCartProduct(
                        productId = data.id!!,
                        productQuantity = count!! + 1,
                        cartId = data.id!!
                    )
                    count = count!! + 1
                },
                modifier = Modifier
                    .size(36.dp)
                    .shadow(elevation = 6.dp, clip = true, shape = CircleShape)
                    .clip(CircleShape)
                    .background(White)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.plus_icon),
                    contentDescription = "Plus", tint = Gray, modifier = Modifier.size(10.dp)
                )
            }

        }
        Text(
            text = data.price.toString() + "TJS",
            color = Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(top = 45.dp, end = 15.dp)
        )

        Box(modifier = Modifier.align(Alignment.TopEnd)) {

            IconButton(
                onClick = { showMenu = !showMenu }, modifier = Modifier
                    .padding(start = 3.dp)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.more_icon),
                    contentDescription = "More",
                    tint = Gray,
                    modifier = Modifier.size(24.dp)

                )
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false },
                modifier = Modifier.align(Alignment.BottomEnd)

            ) {
                DropdownMenuItem(onClick = {
                    favoritesViewModel.favoritesToggle(
                        productId = data.id!!,
                        productType = "product"
                    )
                }) {
                    Text(text = "Добавить в избранное")
                }
                DropdownMenuItem(onClick = { cartViewModel.delCartProduct(cartId = data.id!!) }) {
                    Text(text = "Удалить из списка")
                }
            }
        }
    }
}
