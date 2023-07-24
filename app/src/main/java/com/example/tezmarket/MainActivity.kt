package com.example.tezmarket

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.tezmarket.navigation.AppNavigation
import com.example.tezmarket.presentation.home.TestScreen
import com.example.tezmarket.ui.theme.TezMarketTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val systemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(Color.White)
            TezMarketTheme {
//                TestScreen()
               AppNavigation(navController = navController)
            }
        }
    }
}
