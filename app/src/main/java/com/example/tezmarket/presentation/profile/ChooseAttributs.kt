package com.example.tezmarket.presentation.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tezmarket.R
import com.example.tezmarket.navigation.Screen
import com.example.tezmarket.ui.common.AppThemeButton
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.common.AppThemeTopText
import com.example.tezmarket.ui.theme.Background
import com.example.tezmarket.ui.theme.Primary

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun ChooseAttributs(navController: NavController) {
    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }

    Scaffold(
        topBar = {
            AppThemeTopBar(
                title = "",
                backBtn = true,
                shadow = false,
                icon = "",
                searchText = searchText,
                onValueChange = { searchText.value = it },
                onClick = { /*TODO*/ },
                lazyListState = LazyListState(),
                navController = navController
            )
        },
        bottomBar = { AppThemeButton(text = "Далее") { navController.navigate(Screen.AddAdvertisementScreen.route) } },
        backgroundColor = Background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
        {

            CheckAttr()
        }
    }
}

//-------------------------------------------------------------

private val list = mutableStateListOf<SelectableitemModel>()

@SuppressLint("UnrememberedMutableState")
@Composable
fun CheckAttr() {
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
        item {
            AppThemeTopText(
                text = "Выберите аттрибуты",
                color = Transparent,
                shadow = false,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
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
