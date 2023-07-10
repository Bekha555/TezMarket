package com.example.tezmarket.presentation.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tezmarket.data.remote.model.adress.DataX
import com.example.tezmarket.navigation.Screen
import com.example.tezmarket.presentation.profile.AdressViewModel
import com.example.tezmarket.ui.theme.Primary

@Composable
fun AddressCard(navController: NavController, adress: DataX) {
    var checkedState by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .shadow(10.dp, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(color = White)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 15.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = adress.name ?: "")
                Text(
                    text = "Редактировать",
                    color = Primary,
                    modifier = Modifier.clickable(onClick = {
                        navController.navigate(
                            Screen.AddressAddScreen.passAdressId(
                                adressId = adress.id ?: 1
                            )
                        )
                    })
                )
            }
            Text(
                text = "пр. Рудаки, 100, 52\n${adress.city?.title}, ${adress.zipCode}, ${adress.region}",
                maxLines = 2
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(start = 5.dp, top = 80.dp)
        ) {
            Checkbox(
                checked = checkedState,
                onCheckedChange = { checkedState = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = Primary,
                    uncheckedColor = Color.Gray
                )
            )
            Text(
                text = "Использовать адрес для доставки",
                modifier = Modifier.weight(1f)
            )
        }
    }
}