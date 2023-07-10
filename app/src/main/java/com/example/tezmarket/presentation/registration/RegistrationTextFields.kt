package com.example.tezmarket.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.textFieldColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tezmarket.R
import com.example.tezmarket.ui.theme.Error
import com.example.tezmarket.ui.theme.Success

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NameTextfied(
    textFieldValue: TextFieldValue,
    onTextFieldValueChange: (TextFieldValue) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = textFieldValue,
        onValueChange = onTextFieldValueChange,
        label = { Text(text = "Имя") },
        singleLine = true,
        trailingIcon = {
            if (textFieldValue.text.isNotEmpty() && textFieldValue.text.length >= 3) {
                Icon(
                    painter = painterResource(id = R.drawable.success_icon),
                    contentDescription = "success",
                    modifier = Modifier
                        .size(16.dp),
                    tint = Success
                )

            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = if (textFieldValue.text.isNotEmpty() && textFieldValue.text.length >= 3) {
                ImeAction.Next
            } else
                ImeAction.Default
        ),
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        colors = textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 4.dp)
            .height(65.dp)
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MailTextfield(
    textFieldValue: TextFieldValue,
    onTextFieldValueChange: (TextFieldValue) -> Unit,
) {
    val isError = false
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                value = textFieldValue,
                onValueChange = onTextFieldValueChange,
                label = { Text(text = "Почта") },
                isError = if (textFieldValue.text.isNotEmpty()) {
                    !isError && !textFieldValue.text.contains("@") || !textFieldValue.text.contains(
                        "gmail"
                    ) || !textFieldValue.text.contains(".com")
                } else {
                    isError
                },

                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = if (textFieldValue.text.isNotEmpty() && textFieldValue.text.contains(
                            "@"
                        ) || textFieldValue.text.contains(
                            "gmail"
                        ) || textFieldValue.text.contains(
                            ".com"
                        )
                    ) {
                        ImeAction.Next
                    } else ImeAction.Default
                ),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                trailingIcon = {
                    if (textFieldValue.text.isNotEmpty() && textFieldValue.text.contains("@") && textFieldValue.text.contains(
                            "gmail"
                        ) && textFieldValue.text.contains(
                            ".com"
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.success_icon),
                            contentDescription = "success",
                            modifier = Modifier
                                .size(16.dp),
                            tint = Success
                        )

                    } else if (textFieldValue.text.isNotEmpty() && !textFieldValue.text.contains("@") or !textFieldValue.text.contains(
                            "gmail"
                        ) or !textFieldValue.text.contains(
                            ".com"
                        )
                    )
                        Icon(
                            painter = painterResource(id = R.drawable.error_icon),
                            contentDescription = "error",
                            modifier = Modifier
                                .size(24.dp)
                        )
                },
                colors = textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color.White,
                    errorIndicatorColor = Color.Transparent,
                    errorLabelColor = Error,
                    errorTrailingIconColor = Error
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 4.dp)
                    .height(65.dp)
                    .border(
                        width = 2.dp,
                        color = if (textFieldValue.text.isNotEmpty() && !textFieldValue.text.contains(
                                "@"
                            ) or !textFieldValue.text.contains("gmail") or !textFieldValue.text.contains(
                                ".com"
                            )
                        ) {
                            Error
                        } else Color.Transparent, shape = RoundedCornerShape(4.dp)
                    )
            )
            if (textFieldValue.text.isNotEmpty() && !textFieldValue.text.contains("@") or !textFieldValue.text.contains(
                    "gmail"
                ) or !textFieldValue.text.contains(
                    ".com"
                )
            ) {
                Text(
                    text =
                    "Недействительный адрес электронной почты. \n" +
                            "Должно быть your@email.com",
                    fontSize = 12.sp,
                    color = Error,
                    letterSpacing = 1.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable()
fun PasswordField(
    textFieldValue: TextFieldValue,
    onTextFieldValueChange: (TextFieldValue) -> Unit,
) {
    var passwordVisibility by remember { mutableStateOf(true) }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = textFieldValue,
        onValueChange = onTextFieldValueChange,
        label = { Text("Пароль") },
        singleLine = true,
        colors = textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.White
        ),
        visualTransformation = if (passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (textFieldValue.text.isNotEmpty()) {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 4.dp)
            .height(65.dp)
    )
}
