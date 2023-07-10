package com.example.tezmarket.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tezmarket.R
import com.example.tezmarket.presentation.categories.CategoriesViewModel
import com.example.tezmarket.ui.theme.Gray

@Composable
fun ProfileItem(title: String, subtitle: String, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = Modifier
            .bottomBorder(0.1.dp, Gray)
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 15.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Text(
                text = title,
                fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                fontSize = 17.sp
            )
            Text(
                text = subtitle,
                color = Gray,
                fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                fontSize = 13.sp
            )
        }
        Image(
            modifier = Modifier.size(12.dp),
            painter = painterResource(id = R.drawable.profile_item_button),
            contentDescription = null
        )
    }
}


@Composable
fun ProfileSection(categoriesViewModel: CategoriesViewModel = hiltViewModel()) {

    val user = categoriesViewModel.userUiState.data?.data

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Box(modifier = Modifier.size(70.dp)) {
            Image(
                modifier = Modifier, contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = null
            )
        }
        Column {
            Text(
                text = user?.name.toString(),
                fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                fontSize = 18.sp
            )
            Text(
                text = user?.phone.toString(),
                color = Gray,
                fontFamily = FontFamily(Font(R.font.metropolis_regular))
            )
        }
    }
}

fun Modifier.bottomBorder(strokeWidth: Dp, color: Color) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            val width = size.width
            val height = size.height - strokeWidthPx / 2

            drawLine(
                color = color,
                start = Offset(x = 0f, y = height),
                end = Offset(x = width, y = height),
                strokeWidth = strokeWidthPx
            )
        }
    }
)