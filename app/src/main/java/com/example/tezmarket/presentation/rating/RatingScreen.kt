package com.example.tezmarket.presentation.rating

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tezmarket.R
import com.example.tezmarket.ui.common.AppThemeButton
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.common.AppThemeTopText
import com.example.tezmarket.ui.theme.Background
import com.example.tezmarket.ui.theme.Gray
import com.example.tezmarket.ui.theme.Primary
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RatingScreen(
    navController: NavController,
    productId: Int,
    ratingViewModel: RatingViewModel = hiltViewModel()
) {
    val addProductRating = ratingViewModel.addProductRatingUiState.data
    val productRatingInfo = ratingViewModel.productRatingInfoUiState.data

    LaunchedEffect(key1 = addProductRating, block = {
        ratingViewModel.getProductRating(productId = productId)
    })
    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }

    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()

    var reviewText: String = ""
    var productRating: Int = 1

    val ratingOfFive = productRatingInfo?.ratings
    val ratingList = mutableListOf<Int>()
    ratingList.add(ratingOfFive?.x1 ?: 0)
    ratingList.add(ratingOfFive?.x2 ?: 0)
    ratingList.add(ratingOfFive?.x3 ?: 0)
    ratingList.add(ratingOfFive?.x4 ?: 0)
    ratingList.add(ratingOfFive?.x5 ?: 0)
    Log.e("ratings yusuf", ratingOfFive.toString())


    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetBackgroundColor = Background,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            RatingBottomSheet(
                onClick = {
                    ratingViewModel.addProductRating(
                        productId = productId,
                        productComment = reviewText,
                        productRating = productRating
                    )
                },
                reviewTextValue = { reviewText = it },
                selectedRating = { productRating = it })
        }) {
        Scaffold(
            topBar = {
                AppThemeTopBar(
                    navController = navController,
                    title = "",
                    onClick = {},
                    shadow = false,
                    searchText = searchText,
                    onValueChange = {},
                    icon = "",
                    backBtn = true,
                    modifier = Modifier,
                    lazyListState = LazyListState()
                )
            },
            bottomBar = {
                RatingScreenBottomBar(onClick = {
                    coroutineScope.launch { modalBottomSheetState.show() }
                })
            }) {

            LazyColumn(
                modifier = Modifier
                    .background(Background)
                    .fillMaxSize()
            ) {
                item {
                    AppThemeTopText(
                        text = "Рейтинг и обзоры",
                        color = White,
                        shadow = false,
                        modifier = Modifier
                    )
                }
                item {
                    if (productRatingInfo != null) {
                        RatingSection(productRatingInfo = productRatingInfo, ratingList = ratingList)
                    }
                }
                items(productRatingInfo?.reviews?.data ?: emptyList()) {
                    ReviewCard(it)
                }
            }
        }
    }
}

@Composable
fun RatingScreenBottomBar(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(
                Brush.verticalGradient(
                    colorStops = arrayOf(
                        Pair(0.1f, Transparent),
                        Pair(1f, White)
                    )
                )
            ),
        contentAlignment = Alignment.BottomEnd
    ) {
        Button(
            modifier = Modifier
                .padding(vertical = 30.dp, horizontal = 10.dp)
                .height(50.dp)
                .clip(CircleShape)
                .background(Primary, shape = CircleShape),
            colors = ButtonDefaults.buttonColors(backgroundColor = Primary),
            onClick = onClick
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.add_review_icon),
                    modifier = Modifier
                        .size(19.dp)
                        .padding(start = 3.dp),
                    contentDescription = null
                )
                Text(
                    text = "Оставить отзыв",
                    color = White,
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_regular))
                )
            }
        }
    }
}


@Composable
fun RatingBottomSheet(
    reviewTextValue: (String) -> Unit,
    onClick: () -> Unit,
    selectedRating: (Int) -> Unit
) {
    var reviewText by remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .height(500.dp)
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Divider(
                modifier = Modifier
                    .width(70.dp)
                    .height(8.dp)
                    .clip(RoundedCornerShape(20.dp)),
                color = Gray
            )
            Text(
                text = "Какова ваша оценка?",
                fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                fontSize = 18.sp
            )
            RatingBar(
                modifier = Modifier
                    .size(60.dp)
                    .padding(horizontal = 10.dp),
                startRating = 0,
                selectable = true,
                ratingSelected = {
                    selectedRating(it)
                },
                text = ""
            )
            Text(
                text = "Пожалуйста, поделитесь\n" + "своим мнением о продукте",
                fontSize = 18.sp,
                fontFamily = FontFamily(
                    Font(R.font.metropolis_bold)
                ), textAlign = TextAlign.Center
            )
            TextField(
                modifier = Modifier
                    .width(375.dp)
                    .height(170.dp)
                    .background(color = Transparent, shape = RoundedCornerShape(5.dp))
                    .shadow(elevation = 1.5.dp, shape = RoundedCornerShape(5.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = White,
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent,
                    placeholderColor = Gray,
                    cursorColor = Black
                ),
                value = reviewText,
                onValueChange = {
                    reviewText = it
                    reviewTextValue(it)
                },
                placeholder = { Text(text = "Ваш отзыв") })
            AppThemeButton(
                text = "ОТПРАВИТЬ ОТЗЫВ",
                onClick = onClick
            )
        }
    }
}


@Preview
@Composable
fun RatingScreenPreview() {
    RatingScreen(productId = 1, navController = rememberNavController())
}



