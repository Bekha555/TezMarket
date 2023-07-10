package com.example.tezmarket.ui.common

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tezmarket.ui.theme.Primary

@Composable
fun SizeSelector() {

    val listOfSizes = listOf(
        SizeVariants(name = "XS", id = 1),
        SizeVariants(name = "XL", id = 2),
        SizeVariants(name = "M", id = 3)
    )

    val selectedListOfSizes = mutableListOf<Int>()

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        listOfSizes.forEach {
            var isSelected by remember {
                mutableStateOf(0)
            }
            OutlinedButton(
                onClick = {
                    if (isSelected == 0){
                        isSelected = 1
                        selectedListOfSizes.add(it.id)
                    }
                    else{
                        isSelected = 0
                        selectedListOfSizes.remove(it.id)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isSelected == 0) Color.White else Primary
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = if (isSelected == 0) Color.LightGray else Color.Transparent
                ),
                contentPadding = PaddingValues(horizontal = 40.dp, vertical = 10.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = it.name, color = if (isSelected == 0) Color.Black else Color.White)
            }
        }
    }
}


data class SizeVariants(
    val name: String,
    val id: Int

)


@Preview
@Composable
fun ButtonPreview() {
    SizeSelector()
}