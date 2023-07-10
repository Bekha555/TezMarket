package com.example.tezmarket.presentation.home

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tezmarket.data.remote.model.banners.Data
import com.example.tezmarket.ui.theme.Primary
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield


@ExperimentalPagerApi
@Composable
fun HomeCarousel(banner: List<Data>) {
    val bannerImg = banner
    val pageState = rememberPagerState()
    val isDragged by pageState.interactionSource.collectIsDraggedAsState()

    if (!isDragged) {
        LaunchedEffect(Unit) {
            while (true) {
                yield()
                delay(3000)

                var newPosition = pageState.currentPage + 1


                if (newPosition > bannerImg.lastIndex) {
                    newPosition = 0
                }

                pageState.animateScrollToPage(newPosition)
            }
        }
    }

    Row {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            HorizontalPager(
                state = pageState, count = bannerImg.size
            ) { page ->
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(color = Color.Transparent)
                ) {

                    if (banner.isEmpty()) {
                        ShimmerEffect()
                    } else {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(bannerImg[page].image).crossfade(true).build(),
                            contentDescription = "banner",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                    }

                }
            }
        }
    }
}

@Composable
fun ShimmerEffect() {
    val shimmerColors = listOf(
        Primary.copy(0.7f), Primary.copy(0.1f), Primary.copy(0.7f)
    )
    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f, targetValue = 1000f, animationSpec = infiniteRepeatable(
            tween(
                durationMillis = 2000, easing = FastOutLinearInEasing
            ), RepeatMode.Reverse
        )
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(10f, 10f),
        end = Offset(translateAnimation.value, translateAnimation.value)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    )
}


@Composable
fun Banner(banner: List<Data>) {
    if (banner.isNotEmpty()) {
        val img = banner.last().image

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 5.dp, bottom = 5.dp)
                .height(147.dp)
                .clip(shape = RoundedCornerShape(15.dp))
        ) {
            AsyncImage(
                model = img,
                contentDescription = "banner",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}