package com.example.tezmarket.ui.common


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tezmarket.R


@Composable
fun AppThemeDropdown(
    modifier: Modifier = Modifier,
    dropDownContent: List<String>,
    selectedItem: (String) -> Unit,
    text: String,
) {
    val animationState = remember {
        MutableTransitionState(false)
    }
    var selectedBrand by remember { mutableStateOf(text) }
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
                .shadow(1.dp, shape = RoundedCornerShape(15.dp))
                .clip(RoundedCornerShape(15.dp))
                .background(color = White, shape = RoundedCornerShape(15.dp))
                .clickable(onClick = {
                    animationState.targetState = !animationState.currentState
                })
        ) {
            Text(
                selectedBrand,
                color = Black,
                style = MaterialTheme.typography.subtitle2,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .padding(end = 20.dp, start = 5.dp)
                    .align(Alignment.Center)
            )

            Image(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(12.dp)
                    .align(Alignment.CenterEnd),
                painter = painterResource(id = R.drawable.drop_down_arrow),
                contentDescription = null
            )
        }
        AnimatedVisibility(
            visibleState = animationState,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Column(
                Modifier
                    .padding(
                        vertical = 8.dp,
                    )
                    .shadow(
                        1.dp, shape = RoundedCornerShape(
                            topStart = 8.dp,
                            topEnd = 8.dp,
                            bottomEnd = 15.dp,
                            bottomStart = 15.dp
                        )
                    )
                    .background(
                        color = White,
                        shape = RoundedCornerShape(
                            topStart = 8.dp,
                            topEnd = 8.dp,
                            bottomEnd = 15.dp,
                            bottomStart = 15.dp
                        )
                    )
            ) {
                dropDownContent.forEach { brand ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Max)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                selectedBrand = brand
                                animationState.targetState = false
                                selectedItem(brand)
                            }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.Center)
                        ) {
                            Text(
                                brand,
                                color = Black,
                                style = MaterialTheme.typography.body2
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
internal fun PreviewAppThemeDropdown() {
    AppThemeDropdown(
        modifier = Modifier.padding(horizontal = 20.dp),
        dropDownContent = listOf("Apple", "Samsung", "Xiaomi"),
        selectedItem = {},
        text = "Выберите марку телефонаsrtjsrtjsrtjstjstjsaerharhahaerh",
    )
}