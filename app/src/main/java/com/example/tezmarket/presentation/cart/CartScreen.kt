package com.example.tezmarket.presentation.auth

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieCancellationBehavior
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.tezmarket.R
import com.example.tezmarket.navigation.BottomNavigation
import com.example.tezmarket.navigation.Screen
import com.example.tezmarket.presentation.cart.CartSaleProduct
import com.example.tezmarket.presentation.cart.CartViewModel
import com.example.tezmarket.presentation.cart.PromoCard
import com.example.tezmarket.ui.common.AppThemeButton
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.common.AppThemeTopText
import com.example.tezmarket.ui.theme.Background
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint(
    "UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState",
    "FrequentlyChangedStateReadInComposition"
)
@Composable
fun CartScreen(
    navController: NavController,
    lazyListState: LazyListState,
    cartViewModel: CartViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    LaunchedEffect(key1 = Unit, block = {
        cartViewModel.getAllCart()
        Log.e("debug", "screen is reloaded successfully")
    })
    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden, skipHalfExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()
    val localFocusManager = LocalFocusManager.current
    val carts = cartViewModel.cartProducts.collectAsState()
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.loading_animation)
    )
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        speed = 2f,
        restartOnPlay = false,
        cancellationBehavior = LottieCancellationBehavior.Immediately
    )
    var visible by remember {
        mutableStateOf(true)
    }
    if (lazyListState.firstVisibleItemIndex > 0) {
        visible = false
    }
    if (lazyListState.firstVisibleItemIndex == 0) {
        visible = true
    }
    var promocode by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var sum = 0.0
    for (items in carts.value){sum += items.price!!.toDouble() * items.quantity!!}


    ModalBottomSheetLayout(sheetState = modalBottomSheetState,
        sheetBackgroundColor = Background,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Box(modifier = Modifier
                .height(500.dp)
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        localFocusManager.clearFocus()
                    })
                }) {
                Divider(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .width(70.dp)
                        .height(8.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .align(Alignment.TopCenter), color = Gray
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 52.dp)
                ) {
                    CartTextField(value = promocode, onValueChange = {promocode = it}, onClick = {if (promocode.text.isEmpty()){
                        Toast.makeText(context, "Введите промокод или выберите добавленные", Toast.LENGTH_SHORT).show()} })

                    Text(
                        text = "Ваши промокоды",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Black,
                        modifier = Modifier
                            .padding(top = 30.dp, start = 20.dp, bottom = 30.dp)
                    )

                    LazyColumn() {
                        items(5)
                        {
                            PromoCard()
                        }
                    }
                }
            }
        }) {
        Scaffold(
            topBar = {
                AppThemeTopBar(
                    title = if (!visible) {
                        "Моя корзина"
                    } else {
                        ""
                    },
                    backBtn = false,
                    shadow = false,
                    icon = "",
                    searchText = searchText,
                    onValueChange = {},
                    onClick = { /*TODO*/ },
                    navController = rememberNavController(),
                    modifier = Modifier,
                    lazyListState = LazyListState()
                )
            },
            bottomBar = {
                Column {
                    if (carts.value.isNotEmpty()) {
                        AppThemeButton(text = "ПРОВЕРИТЬ") {
                            navController.navigate(Screen.CartCheckout.route)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    BottomNavigation(navController = navController)
                }
            },
            backgroundColor = Background
        )
        { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                if (cartViewModel.cartUiState.data?.data?.isEmpty() == true) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = "Карзина пуста",
                            color = Gray,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .align(
                                    Alignment.Center
                                )
                                .padding(horizontal = 20.dp)
                        )
                    }
                }
                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 0.dp, bottom = 20.dp)
                    //.verticalScroll(rememberScrollState())
                ) {
                    item {
                        AppThemeTopText(
                            text = "Моя корзина",
                            color = Transparent,
                            shadow = false,
                            modifier = Modifier
                        )
                    }
                    items(carts.value) {
                        CartSaleProduct(navController = navController, data = it)
                    }
                    item {
                        Column {
                            if (carts.value.isNotEmpty()) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 20.dp)
                                        .padding(top = 10.dp)
                                        .shadow(
                                            elevation = 1.5.dp,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .background(color = White, shape = RoundedCornerShape(8.dp))
                                        .requiredHeight(52.dp)
                                        .clickable(onClick = { coroutineScope.launch { modalBottomSheetState.show() } })
                                )
                                {
                                    Text(
                                        text = "Введите ваш промокод",
                                        color = Gray,
                                        modifier = Modifier
                                            .align(
                                                Alignment.CenterStart
                                            )
                                            .padding(start = 25.dp)
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 25.dp)
                                        .padding(top = 10.dp)
                                ) {
                                    Text(
                                        text = "Общая сумма:",
                                        color = Gray,
                                        fontSize = 14.sp,
                                        modifier = Modifier
                                            .align(Alignment.CenterStart)
                                    )

                                    Text(
                                        text = sum.toInt().toString() + "TJS",
                                        color = Black,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.align(Alignment.CenterEnd)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


val LazyListState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun CartTextField(value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit, onClick: () -> Unit) {
    var promocode by remember {
        mutableStateOf(TextFieldValue(""))
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontSize = 14.sp,
        ),
        placeholder = { Text(text = "Введите ваш промокод", color = Gray) },
        trailingIcon = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(Black)
                    .clickable(onClick = onClick)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_right_icon),
                    contentDescription = null,
                    tint = White,
                    modifier = Modifier
                        .size(12.dp)
                )
            }
        },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Transparent,
            unfocusedIndicatorColor = Transparent,
            backgroundColor = White,
            textColor = Black
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(onSearch = { keyboardController?.hide() }),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .requiredHeight(52.dp)
            .clip(
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 30.dp,
                    bottomStart = 8.dp,
                    bottomEnd = 30.dp
                )
            )
    )
}


@Preview
@Composable
fun ShowCartScreen() {
    CartScreen(navController = rememberNavController(), lazyListState = LazyListState())
}