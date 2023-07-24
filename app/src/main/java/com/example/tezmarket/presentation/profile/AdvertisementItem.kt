package com.example.tezmarket.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tezmarket.R
import com.example.tezmarket.data.remote.model.myadvertisements.Data
import com.example.tezmarket.ui.theme.Gray
import com.example.tezmarket.ui.theme.LightGray
import com.example.tezmarket.ui.theme.Purple200
import kotlin.math.roundToInt

@ExperimentalMaterialApi
@Composable
fun AdvertisementItem(onClick: () -> Unit, data: Data) {
    var bgColor by remember { mutableStateOf(Purple200) }
    val squareSize = 65.dp
    val swipeAbleState = rememberSwipeableState(initialValue = 0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, -sizePx to 1)
    val advertisements = data

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(bottom = 15.dp)
            .shadow(4.dp, shape = RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(color = LightGray, shape = RoundedCornerShape(20.dp))
            .swipeable(
                swipeAbleState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
            .clickable(onClick = onClick)
    ) {
        IconButton(
            onClick = { /*TODO*/ }, modifier = Modifier
                .padding(end = 10.dp)
                .align(
                    Alignment.CenterEnd
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.delete_icon),
                contentDescription = "delete", modifier = Modifier.size(20.dp)
            )
        }

        Box(modifier = Modifier
            .offset {
                IntOffset(swipeAbleState.offset.value.roundToInt(), 0)
            }
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .height(90.dp)
            .fillMaxHeight()
            .background(Color.White)
            .align(Alignment.CenterStart)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = advertisements.name.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(start = 15.dp, top = 10.dp)
                        .align(Alignment.TopStart)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .align(Alignment.CenterStart)
                ) {
                    Text(text = "категория:", fontSize = 14.sp, color = Gray)
                    Text(
                        text = advertisements.category?.name.toString(),
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 15.dp, bottom = 15.dp)
                        .align(Alignment.BottomStart)
                ) {
                    Text(text = "цена:", fontSize = 14.sp, color = Gray)
                    Text(
                        text = advertisements.price.toString() + "c",
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ }, modifier = Modifier
                        .padding(end = 15.dp)
                        .align(Alignment.CenterEnd)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.edit_icon),
                        contentDescription = "edit", modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}