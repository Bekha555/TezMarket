package com.example.tezmarket.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tezmarket.R
import com.example.tezmarket.ui.theme.Gray
import com.example.tezmarket.ui.theme.Primary


@Composable
fun BottomNavigation(
    navController: NavController,
) {
    val items = listOf(
        NavScreen.HomeScreen,
        NavScreen.CategoriesScreen,
        NavScreen.CartScreen,
        NavScreen.FavoriteScreen,
        NavScreen.ProfileScreen
    )

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        BottomNavigation {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEach { screen ->
                BottomNavigationItem(
                    modifier = Modifier
                        .background(color = White)
//                        .clickable(
//                            indication = null,
//                            interactionSource = remember { MutableInteractionSource() }) {
//
//                        }
                    ,
                    icon = {
                        Icon(
                            painter = painterResource(
                                id = if (currentRoute == screen.route) {
                                    screen.selected_icon
                                } else screen.icon
                            ),
                            modifier = Modifier
                                .size(26.dp),
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            text = screen.title,
                            fontSize = 9.sp,
                            fontFamily = FontFamily(
                                Font(R.font.metropolis_regular)
                            ),
                            fontWeight = FontWeight.Light,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    selected = currentRoute == screen.route,
                    selectedContentColor = Primary,
                    unselectedContentColor = Gray,
                    alwaysShowLabel = true,
                    onClick = {
                        navController.navigate(screen.route) {
                            launchSingleTop = true
                            restoreState = false
                        }
                    }
                )
            }
        }
    }

}


private object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Transparent

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.0f, 0.0f, 0.0f, 0.0f)
}


@Preview(showBackground = true)
@Composable
fun BBB() {
    val navController = rememberNavController()
    BottomNavigation(navController = navController)
}
