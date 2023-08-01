package com.example.tezmarket.presentation.rating

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tezmarket.R
import com.example.tezmarket.data.remote.model.productrating.ProductRatingData
import com.example.tezmarket.ui.theme.Primary
import com.example.tezmarket.ui.theme.ReviewBarColor

@Composable
fun RatingSection(productRatingInfo: ProductRatingData, ratingList: List<Int>) {
    var isChecked by remember { mutableStateOf(false) }
    var a = 0
    val context = LocalContext.current
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 40.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(bottom = 40.dp)
            ) {
                Text(
                    text = productRatingInfo.ratingAvg.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 50.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_regular))
                )
                Text(
                    text = "${productRatingInfo.ratingCount} оценки",
                    fontFamily = FontFamily(Font(R.font.metropolis_regular)),
                    color = Color.Gray
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                for (i in ratingList) {
                    a += 1
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        ConstantRatingBar(
                            modifier = Modifier
                                .padding(horizontal = 2.dp, vertical = 2.dp)
                                .size(15.dp),
                            startRating = a
                        )
                        ReviewProgressBar(
                            progress = if (ratingList.sum() == 0) {
                                0f
                            } else (i / ratingList.sum()).toFloat()
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                text = i.toString(),
                                modifier = Modifier.wrapContentWidth(align = Alignment.End)
                            )
                        }
                    }
                }

            }
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = productRatingInfo?.reviews?.data?.size.toString() + " отзывов",
            fontSize = 30.sp,
            fontFamily = FontFamily(
                Font(R.font.metropolis_bold)
            ),
            fontWeight = FontWeight.Bold,
        )
//        Row(
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Checkbox(
//                checked = isChecked,
//                onCheckedChange = { isChecked = !isChecked },
//                colors = CheckboxDefaults.colors(checkedColor = Color.Black)
//            )
//            Text(
//                text = "С фото",
//                fontFamily = FontFamily(Font(R.font.metropolis_regular))
//            )
//        }
    }
}

@Composable
fun ReviewProgressBar(progress: Float) {
    LinearProgressIndicator(
        progress = progress,
        color = Primary,
        backgroundColor = Color.Transparent,
        modifier = Modifier
            .padding(vertical = 5.dp)
            .clip(CircleShape)
            .height(8.dp)
            .width(100.dp)
    )
}



