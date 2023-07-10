package com.example.tezmarket.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tezmarket.R
import com.example.tezmarket.ui.common.*
import com.example.tezmarket.ui.theme.Background

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ForgetPasswordScreen(navController: NavController) {
    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }

    Scaffold(
        backgroundColor = Background,
        topBar = {
            AppThemeTopBar(
                title = "",
                navController = navController,
                shadow = false,
                onClick = {},
                searchText = searchText,
                onValueChange = {},
                backBtn = true,
                icon = "",
                modifier = Modifier,
                lazyListState = LazyListState()
            )
        }) {

        val localFocusManager = LocalFocusManager.current

        var email by remember {
            mutableStateOf(TextFieldValue(""))
        }

        val maxLength = 30

        Column(modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                localFocusManager.clearFocus()
            })
        }) {
            AppThemeTopText(text = "Забыли пароль", color = Transparent, shadow = false, modifier = Modifier)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Пожалуйста, введите Ваш адрес электронной почты. " +
                            "Вы получите ссылку для создания нового" +
                            " пароля по электронной почте.",
                    textAlign = TextAlign.Start,
                    fontFamily = FontFamily(
                        Font(R.font.metropolis_regular)
                    ),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    maxLines = 3,
                    modifier = Modifier.padding(top = 80.dp),
                    letterSpacing = 1.sp
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            MailTextfield(
                textFieldValue = email,
                onTextFieldValueChange = {
                    if (it.text.length <= maxLength) {
                        email = it
                    }
                })
            Spacer(modifier = Modifier.height(55.dp))
            AppThemeButton(text = "Отправить") {}
        }
    }
}

