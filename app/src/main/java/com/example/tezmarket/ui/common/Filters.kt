package com.example.tezmarket.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tezmarket.R
import com.example.tezmarket.ui.theme.Background
import com.example.tezmarket.ui.theme.Shadow


@Suppress("UNUSED_EXPRESSION")
@Composable
fun Filters(onClick: () -> Unit, grid: () -> Unit, filter: () -> Unit) {

    var selected by remember {
        mutableStateOf(0)
    }

    val colorStops = arrayOf(
        0.0f to Shadow,
        1f to Color.Transparent
    )

    Box(
        modifier = Modifier
            .background(White)
            .fillMaxWidth()
            .height(70.dp)
            .padding(top = 10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = filter, modifier = Modifier
                                .size(23.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.filter_icon),
                                contentDescription = "filter", modifier = Modifier
                                    .size(25.dp)
                                    .padding(end = 3.dp)
                            )
                        }
                        Text(
                            text = "Фильтры",
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.metropolis_regular))
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.data_icon),
                            contentDescription = "data", modifier = Modifier
                                .size(23.dp)
                                .padding(end = 3.dp)
                        )
                        Text(
                            text = "Цена: от низкой к высокой",
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.metropolis_regular))
                        )
                    }

                    IconButton(
                        onClick = {

                            selected += 1
                            grid()

                        }, modifier = Modifier.size(20.dp)
                    ) {
                        if (selected % 2 == 0){
                            Icon(
                                painter = painterResource(id = R.drawable.view_module_icon),
                                contentDescription = "module", modifier = Modifier
                                    .size(23.dp)
                            )
                        }
                        else{
                            Icon(
                                painter = painterResource(id = R.drawable.view_list_icon),
                                contentDescription = "module", modifier = Modifier
                                    .size(23.dp)
                            )
                        }

                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(10.dp)
                    .background(Brush.verticalGradient(colorStops = colorStops))
            )
        }
    }
}

@Composable
fun ButtonItem(onClick: () -> Unit, text: String) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Black)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = White,
            fontFamily = FontFamily(Font(R.font.metropolis_regular))
        )
    }
}


@Preview
@Composable
fun ShowButtons() {
    Filters(onClick = { /*TODO*/ }, grid = {}, filter = {})
}

