package com.example.tezmarket.presentation.rating

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tezmarket.R
import com.example.tezmarket.data.remote.model.productrating.DataX

@Composable
fun ReviewCard(reviewInfo: DataX) {
    Box(modifier = Modifier.padding(15.dp)) {
        Box(
            modifier = Modifier
                .padding(start = 20.dp, top = 20.dp, bottom = 20.dp, end = 5.dp)
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(15.dp))
                .background(Color.White, RoundedCornerShape(15.dp))

        ) {
            Column(
                modifier = Modifier.padding(30.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = reviewInfo.client?.name ?: "Сумая",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    RatingBar(
                        modifier = Modifier
                            .padding(horizontal = 2.dp, vertical = 2.dp)
                            .size(15.dp),
                        startRating = reviewInfo.rating?.toInt() ?: 0,
                        selectable = false,
                        ratingSelected = {},
                        text = reviewInfo.rating.toString()
                    )
                    Text(
                        text = "Июль 5, 2023",
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center
                    )
                }
                Text(
                    text = reviewInfo.comment ?: "",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                    lineHeight = 22.sp
                )
            }
        }
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
                .align(Alignment.TopStart),
            painter = painterResource(id = R.drawable.review_profile_icon),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}

