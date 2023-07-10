package com.example.tezmarket.presentation.brand

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tezmarket.R
import com.example.tezmarket.ui.theme.Primary


private val list = mutableStateListOf<SelectableitemModel>()

@SuppressLint("UnrememberedMutableState")
@Composable
fun BrandFilterList() {
    ContentView(list = list)

    list.clear()
    val tempList = mutableListOf<SelectableitemModel>()
    for (i in 1 until 20) {
        tempList.add(SelectableitemModel("Content $i", mutableStateOf(false)))
    }
    list.addAll(tempList)

}


@Composable
private fun ContentView(list: MutableList<SelectableitemModel>) {
    LazyColumn(state = rememberLazyListState())
    {
        items(items = list) { item ->
            ListItemView(model = item)
        }
    }


}

@Composable
private fun ListItemView(model: SelectableitemModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 6.dp, horizontal = 16.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null, onClick = { model.toggle() })
    ) {
        Text(
            text = model.content,
            fontSize = 16.sp,
            color = if (model.selected.value) {
                Primary
            } else {
                Color.Black
            },
            fontFamily = FontFamily(
                if (model.selected.value) {
                    Font(R.font.metropolis_bold)
                } else {
                    Font(R.font.metropolis_regular)
                }
            ),
            modifier = Modifier.align(Alignment.CenterStart)
        )

        Checkbox(
            checked = model.selected.value,
            onCheckedChange = {
                model.toggle()
            },
            colors = CheckboxDefaults.colors(
                checkedColor = Primary,
                uncheckedColor = Color.Gray
            ),
            modifier = Modifier
                .align(Alignment.CenterEnd)
        )
    }
}

data class SelectableitemModel(
    var content: String,
    var selected: MutableState<Boolean>
) {
    fun toggle() {
        selected.value = !selected.value

    }
}
