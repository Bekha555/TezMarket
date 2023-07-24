package com.example.tezmarket.presentation.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tezmarket.R
import com.example.tezmarket.navigation.Screen
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.common.AppThemeTopText
import com.example.tezmarket.ui.theme.Background
import com.example.tezmarket.ui.theme.Primary

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyAdvertisementsScreen(
    navController: NavController,
    advertisementViewModel: AdvertisementViewModel = hiltViewModel()
) {
    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }

    val advertisements = advertisementViewModel.myAdvertisementsUiState.data?.data


    Scaffold(
        topBar = {
            AppThemeTopBar(
                title = "",
                backBtn = true,
                shadow = false,
                icon = "",
                searchText = searchText,
                onValueChange = {},
                onClick = { /*TODO*/ },
                lazyListState = LazyListState(),
                navController = navController
            )
        },
        bottomBar = { AdvertisementBottomBar(onClick = { navController.navigate(Screen.AddAdvertisementScreen.route) }) },
        backgroundColor = Background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
        ) {
            AppThemeTopText(
                text = "Мои объявления",
                color = Transparent,
                shadow = false,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(25.dp))

            if (advertisements != null) {
                for (data in advertisements){
                    AdvertisementItem({}, data = data)
                }
            }


        }
    }
}


@Composable
fun AdvertisementBottomBar(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(
                Brush.verticalGradient(
                    colorStops = arrayOf(
                        Pair(0.1f, Transparent),
                        Pair(1f, White)
                    )
                )
            ),
        contentAlignment = Alignment.BottomEnd
    ) {
        Button(
            modifier = Modifier
                .padding(vertical = 30.dp, horizontal = 10.dp)
                .height(50.dp)
                .clip(CircleShape)
                .background(Primary, shape = CircleShape),
            colors = ButtonDefaults.buttonColors(backgroundColor = Primary),
            onClick = onClick
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.add_review_icon),
                    modifier = Modifier
                        .size(19.dp)
                        .padding(start = 3.dp),
                    contentDescription = null
                )
                Text(
                    text = "Добавить",
                    color = White,
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.metropolis_regular))
                )
            }
        }
    }
}






