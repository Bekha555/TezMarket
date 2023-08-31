package com.example.tezmarket.presentation.profile

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tezmarket.R
import com.example.tezmarket.data.getFileFromPath
import com.example.tezmarket.data.remote.model.addadvertisement.Attribute
import com.example.tezmarket.navigation.Screen
import com.example.tezmarket.presentation.profile.AdressViewModel_Factory.create
import com.example.tezmarket.ui.common.AppThemeButton
import com.example.tezmarket.ui.common.AppThemeDropdown
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.theme.Background
import com.example.tezmarket.ui.theme.Primary
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


@SuppressLint(
    "UnusedMaterialScaffoldPaddingParameter", "RememberReturnType",
    "CoroutineCreationDuringComposition", "StateFlowValueCalledInComposition"
)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddAdvertisementScreen(
    navController: NavController,
    advertisementViewModel: AdvertisementViewModel = hiltViewModel()
) {

    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }
    var name by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var description by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var price by remember {
        mutableStateOf(TextFieldValue("0"))
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val mutableUriList = remember { mutableStateListOf<Uri?>() }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uris ->
            uris.forEach {
                mutableUriList.add(it)
            }
        }
    )

    val interactionScope = remember {
        MutableInteractionSource()
    }
    val localFocusManager = LocalFocusManager.current
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var imagesFile = mutableListOf<File>()

    mutableUriList.forEach { uri ->
        val file = uri?.let { getFileFromPath(it, context) }
        if (file != null) {
            imagesFile.add(file)
        }
    }



    Scaffold(
        topBar = {
            AppThemeTopBar(
                title = "Добавить объявление",
                backBtn = true,
                shadow = false,
                icon = "",
                searchText = searchText,
                onValueChange = {},
                onClick = { /*TODO*/ },
                lazyListState = rememberLazyListState(),
                navController = navController
            )
        }, bottomBar = {
            AppThemeButton(text = "Сохранить") {
                advertisementViewModel.tempImageLinks.clear()
                imagesFile.forEach { file ->
                    advertisementViewModel.uploadFile(
                        MultipartBody.Part.createFormData(
                            "file", file.name, file
                                .asRequestBody("image/*".toMediaTypeOrNull())
                        )
                    )
                }
                Toast.makeText(context, "Объявление отправлено в модерацию", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.MyAdvertisementsScreen.route){
                    popUpTo(Screen.MyAdvertisementsScreen.route){inclusive = true}
                }
            }
        }, backgroundColor = Background
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .verticalScroll(state = rememberScrollState())
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }) {
            Text(
                text = "Название",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Text(
                text = "Добавьте минимум 15, символов чтобы сделать его более привлекательным и удобным для покупателя",
                maxLines = 3,
                fontSize = 14.sp,
                color = Gray,
                modifier = Modifier.padding(end = 100.dp, start = 20.dp)
            )
            TextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text(text = "Название", fontSize = 14.sp, color = Gray) },
                singleLine = true,
                trailingIcon = { },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = if (name.text.isNotEmpty() && name.text.length >= 3) {
                        ImeAction.Next
                    } else
                        ImeAction.Default
                ),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color.White
                ), textStyle = TextStyle(fontSize = 14.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(horizontal = 20.dp, vertical = 8.dp)
                    .shadow(1.dp, shape = RoundedCornerShape(15.dp))
                    .clip(RoundedCornerShape(15.dp))

            )
            Text(
                text = "Описание",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp)
            )
            Text(
                text = "Ключевые фразы обязательно вставлять в описание товара. Укажите дополнительные или важные детали",
                maxLines = 3,
                fontSize = 14.sp,
                color = Gray,
                modifier = Modifier.padding(end = 100.dp, start = 20.dp)
            )
            TextField(
                value = description,
                onValueChange = { description = it },
                placeholder = { Text(text = "Имя", fontSize = 14.sp, color = Gray) },
                singleLine = false,
                trailingIcon = { },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = if (description.text.isNotEmpty() && description.text.length >= 10) {
                        ImeAction.Next
                    } else
                        ImeAction.Default
                ),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color.White
                ), textStyle = TextStyle(fontSize = 14.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(horizontal = 20.dp, vertical = 8.dp)
                    .shadow(1.dp, shape = RoundedCornerShape(15.dp))
                    .clip(RoundedCornerShape(15.dp))

            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 15.dp)
            ) {
                Text(
                    text = "Цена",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                )
                TextField(
                    value = price,
                    onValueChange = { price = it },
                    placeholder = { Text(text = "Цена", fontSize = 12.sp, color = Gray) },
                    singleLine = true,
                    trailingIcon = { },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        backgroundColor = Color.White
                    ), textStyle = TextStyle(fontSize = 12.sp),
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp)
                        .shadow(1.dp, shape = RoundedCornerShape(15.dp))
                        .clip(RoundedCornerShape(15.dp))
                        .align(Alignment.CenterEnd)

                )

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 15.dp)
            ) {
                Text(
                    text = "Категория",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 14.dp)
                )
                AppThemeDropdown(
                    dropDownContent = listOf("Брюки", "Юбки", "Платья"),
                    selectedItem = {},
                    text = "Выберите категорию",
                    modifier = Modifier
                        .width(200.dp)
                        .align(Alignment.TopEnd)
                )
            }
            Text(
                text = "Загрузите Изображения",
                fontSize = 20.sp,
                maxLines = 2,
                color = Primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .width(170.dp)
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp)
            )
            Text(
                text = "Избегайте продажи контрафактной продукции/нарушение прав интелектуальной собственности, чтобы ваши продукты не были удалены",
                maxLines = 4,
                fontSize = 14.sp,
                color = Gray,
                modifier = Modifier.padding(end = 70.dp, start = 20.dp)
            )


            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(mutableUriList) {
                    AsyncImage(
                        modifier = Modifier
                            .size(110.dp)
                            .clip(RoundedCornerShape(5.dp)),
                        model = it,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
                item {
                    Column(
                        modifier = Modifier
                            .width(110.dp)
                            .background(color = White, shape = RoundedCornerShape(5.dp))
                            // .padding(10.dp)
                            .clickable(
                                indication = null,
                                interactionSource = interactionScope
                            ) {
                                launcher.launch("image/*")
                            },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Image(
                            modifier = Modifier.size(50.dp),
                            painter = painterResource(id = R.drawable.add_photo_icon),
                            contentDescription = null
                        )
                        Text(
                            text = "Добавьте свои \n" +
                                    "фотографии",
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.metropolis_bold)),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }

    if (advertisementViewModel.uploadFilesUiState.data?.filePath?.isNotEmpty() == true) {
        if (advertisementViewModel.tempImageLinks.size == imagesFile.size) {
            LaunchedEffect(key1 = true, block = {
                val mapData = HashMap<String, Any>()
                mapData["title"] = mapOf("ru" to name.text)
                mapData["price"] = price.text.toInt()
                mapData["category_id"] = 2
                mapData["description"] = mapOf("ru" to description.text)
                mapData["images"] = advertisementViewModel.tempImageLinks
                mapData["attrs"] = listOf(Attribute(1, "", "", "a"))
                advertisementViewModel.addAdvertisement(mapData)
                advertisementViewModel.tempImageLinks.clear()
            })
        }
    }
}





