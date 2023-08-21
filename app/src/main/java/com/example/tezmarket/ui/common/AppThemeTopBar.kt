package com.example.tezmarket.ui.common

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tezmarket.R
import com.example.tezmarket.ui.theme.LightGray
import com.example.tezmarket.ui.theme.Shadow

@SuppressLint("UnrememberedMutableState")
@Composable
fun TopBar(
    title: String,
    backBtn: Boolean,
    icon: String,
    searchText: MutableState<TextFieldValue>,
    onValueChange: (TextFieldValue) -> Unit,
    onClick: () -> Unit,
    color: Color = Transparent,
    navController: NavController
) {
    val showSearchField = remember { mutableStateOf(false) }
//    val text = remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = color)
    ) {
        Crossfade(
            targetState = showSearchField.value,
            animationSpec = tween(easing = EaseInOut)
        ) { targetState ->
            if (!targetState) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (backBtn) {
                        IconButton(
                            onClick = { navController.navigateUp() }, modifier = Modifier.align(
                                Alignment.CenterStart)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.back_button_icon),
                                contentDescription = "back",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.width(24.dp))
                    }

                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )

                    IconButton(
                        onClick = {
                            if (icon.contains("search")) {
                                showSearchField.value = true
                            } else {
                                onClick()
                            }
                        },
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        if (icon.isNotEmpty()) {
                            Icon(
                                imageVector = if (icon.contains("search")) {
                                    Icons.Default.Search
                                } else {
                                    Icons.Default.Share
                                },
                                contentDescription = "search",
                            )
                        }
                    }
                }
            } else {
                TextField(
                    value = searchText.value,
                    onValueChange = onValueChange,
                    placeholder = {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null
                            )
                            Text(text = "Поиск товаров")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 10.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .focusRequester(focusRequester),
                    singleLine = true,
                    keyboardOptions =
                    KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions =
                    KeyboardActions(onSearch = { /* Действие при нажатии кнопки поиска */ }),
                    trailingIcon = {
                        IconButton(onClick = {
                            if (searchText.value.text.isEmpty()) {
                                showSearchField.value = false
                            } else {
                                searchText.value = TextFieldValue("")
                            }
                        }) {
                            Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear")
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Transparent,
                        unfocusedIndicatorColor = Transparent,
                        backgroundColor = LightGray
                    )
                )
                DisposableEffect(Unit) {
                    focusRequester.requestFocus()
                    onDispose { }
                }
            }
        }
    }
}


@SuppressLint("SuspiciousIndentation")
@Composable
fun AppThemeTopBar(
    title: String,
    backBtn: Boolean,
    shadow: Boolean,
    icon: String,
    searchText: MutableState<TextFieldValue>,
    onValueChange: (TextFieldValue) -> Unit,
    onClick: () -> Unit,
    color: Color = Transparent ,
    modifier: Modifier = Modifier,
    lazyListState: LazyListState,
    navController: NavController
) {
    val colorStops = arrayOf(
        0.0f to Shadow,
        1f to Color.Transparent
    )

    Column {
        TopBar(
            title = title,
            backBtn = backBtn,
            icon = icon,
            searchText = searchText,
            onValueChange = onValueChange,
            onClick = onClick,
            color = color,
            navController = navController
        )
        if (shadow)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(20.dp)
                    .background(Brush.verticalGradient(colorStops = colorStops))
            )
    }
}


@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun Show() {
    AppThemeTopBar(
        title = "Категории",
        backBtn = true,
        shadow = false,
        icon = "search",
        onClick = {},
        searchText = mutableStateOf(
            TextFieldValue("")
        ),
        onValueChange = {},
        modifier = Modifier,
        lazyListState = LazyListState(),
        navController = rememberNavController()
    )
}
