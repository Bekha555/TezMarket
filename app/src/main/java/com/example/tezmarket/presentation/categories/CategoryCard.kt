package com.example.tezmarket.presentation.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tezmarket.R
import com.example.tezmarket.data.remote.model.categories.Data

@Composable
fun CategoryCard(modifier: Modifier = Modifier, category: Data) {
    Box(modifier = modifier.fillMaxWidth()) {
        Text(
            text = category.name.toString(),
            fontSize = 16.sp,
            fontFamily = FontFamily(
                Font(R.font.metropolis_regular)
            ),
            modifier = Modifier
                .padding(start = 40.dp)
                .padding(vertical = 17.dp)
        )
        Divider(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}