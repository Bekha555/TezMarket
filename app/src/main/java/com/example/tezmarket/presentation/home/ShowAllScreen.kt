package com.example.tezmarket.presentation.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.tezmarket.R
import com.example.tezmarket.navigation.Screen
import com.example.tezmarket.presentation.categories.CategoriesViewModel
import com.example.tezmarket.ui.common.AppThemeTopBar
import com.example.tezmarket.ui.common.Filters
import com.example.tezmarket.ui.common.SaleProduct
import com.example.tezmarket.ui.common.SaleProductList
import com.example.tezmarket.ui.theme.Background
import com.example.tezmarket.ui.theme.Gray
import com.example.tezmarket.ui.theme.Primary
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShowAllScreen(
    navController: NavController,
    productName: Int,
    homeViewModel: HomeViewModel = hiltViewModel(),
    categoriesViewModel: CategoriesViewModel = hiltViewModel()
) {

    var visible by remember {
        mutableStateOf(true)
    }

    var search = remember {
        mutableStateOf(TextFieldValue(""))
    }

    var filterOption by remember {
        mutableStateOf("По умолчанию")
    }

    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val context = LocalContext.current
    var categoryName: String = ""
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = true, block = {
        if (productName >= 0) {
            homeViewModel.getProductsByCategory(categoryId = productName)
        }
        homeViewModel.getFilterData()
    })

    val categoriesList = categoriesViewModel.categoriesUiState.data?.data ?: emptyList()
    if (categoriesList.isNotEmpty() && productName > 0) {
        Toast.makeText(
            context,
            "product Name ${productName - 1}, category name ${categoriesList.get(productName - 1).name}",
            Toast.LENGTH_SHORT
        ).show()
        categoryName = categoriesList[productName - 1].name.toString()
    }

    val productsByCategory = homeViewModel.productsByCategory.collectAsLazyPagingItems()
    val discProducts = homeViewModel.discProductItems.collectAsLazyPagingItems()
    val products = homeViewModel.productItems.collectAsLazyPagingItems()
    val shopsItems = homeViewModel.shopsItems.collectAsLazyPagingItems()
    val filteredProducts = homeViewModel.filteredDataUiState.data?.data ?: emptyList()
    val filterData = homeViewModel.filterUiState.data?.get(1)?.data ?: emptyList()
    val advertisements = homeViewModel.advertisementItems.collectAsLazyPagingItems()


    val filterDataMap = hashMapOf<String, String>()
    filterDataMap["По умолчанию"] = ""
    filterData.forEach {
        filterDataMap[it.label.toString()] = it.value.toString()
    }

        ModalBottomSheetLayout(
            sheetState = modalBottomSheetState,
            sheetBackgroundColor = Background,
            sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
            sheetContent = {
                FilterBottomSheet(
                    options = filterDataMap,
                    categoryId = productName,
                    optionSelected = {
                        filterOption = it
                    })
            }) {
            Scaffold(
                topBar = {
                    AppThemeTopBar(
                        navController = navController,
                        backBtn = true,
                        icon = "search",
                        shadow = false,
                        onClick = { },
                        title = when (productName) {
                            -1 -> {
                                "Новое"
                            }

                            -2 -> {
                                "Распродажа"
                            }

                            -3 -> {
                                "Магазины"
                            }

                            -4 -> {
                                "Объявления"
                            }

                            else -> {
                                categoryName
                            }
                        },
                        lazyListState = LazyListState(),
                        searchText = search,
                        onValueChange = {
                            search.value = it
                            homeViewModel.updateData("title", it.text)
                            if (productName != -1 && productName != -2 && productName != -3 && productName != 0) {
                                homeViewModel.updateData("category_id", productName - 1)
                            }
                            homeViewModel.getFilteredProducts()
                        }
                    )
                },
                backgroundColor = Background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Filters(
                        onClick = { },
                        grid = {
                            visible = !visible
                            Toast.makeText(context, "grid icon is pressed", Toast.LENGTH_SHORT)
                                .show()
                        },
                        filter = {
                            coroutineScope.launch { modalBottomSheetState.show() }
                        },
                        filterOption = filterOption
                    )
                    AnimatedVisibility(visible = visible) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            if (search.value.text.isNotEmpty() || filterOption != "По умолчанию") {
                                Toast.makeText(
                                    context,
                                    filteredProducts.toString(),
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                if (homeViewModel.filteredDataUiState.isLoading) {
                                    item {
                                        PaginationLoading()
                                    }
                                } else {

                                    gridItems(
                                        filteredProducts.size,
                                        nColumns = 2,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        filteredProducts[it].let {
                                            SaleProduct(
                                                sale_label = "",
                                                width = 160.dp,
                                                onClick = {
                                                    navController.navigate(
                                                        Screen.ProductDetailsScreen.passProductDetails(
                                                            it!!.id ?: 1
                                                        )
                                                    )
                                                },
                                                product = it
                                            )
                                        }
                                    }
                                }
                            } else {

                                when (productName) {
                                    -1 -> {
                                        gridItems(
                                            products.itemCount,
                                            nColumns = 2,
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            products[it].let {
                                                SaleProduct(
                                                    sale_label = "",
                                                    width = 160.dp,
                                                    onClick = {
                                                        navController.navigate(
                                                            Screen.ProductDetailsScreen.passProductDetails(
                                                                it!!.id ?: 1
                                                            )
                                                        )
                                                    },
                                                    product = it
                                                )
                                            }
                                        }
                                    }

                                    -2 -> {
                                        gridItems(
                                            discProducts.itemCount,
                                            nColumns = 2,
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            discProducts[it].let {
                                                SaleProduct(
                                                    sale_label = "",
                                                    width = 160.dp,
                                                    onClick = {
                                                        navController.navigate(
                                                            Screen.ProductDetailsScreen.passProductDetails(
                                                                it!!.id ?: 1
                                                            )
                                                        )
                                                    },
                                                    product = it
                                                )
                                            }
                                        }
                                    }

                                    -3 -> {
                                        gridItems(
                                            shopsItems.itemCount,
                                            nColumns = 2,
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            shopsItems[it].let {
                                                SaleProduct(
                                                    sale_label = "",
                                                    width = 160.dp,
                                                    onClick = {
                                                        navController.navigate(
                                                            Screen.ProductDetailsScreen.passProductDetails(
                                                                it!!.id ?: 1
                                                            )
                                                        )
                                                    },
                                                    product = it
                                                )
                                            }
                                        }
                                    }

                                    -4 -> {
                                        gridItems(
                                            advertisements.itemCount,
                                            nColumns = 2,
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            advertisements[it].let {
                                                SaleProduct(
                                                    sale_label = "",
                                                    width = 160.dp,
                                                    onClick = {
                                                        navController.navigate(
                                                            Screen.ProductDetailsScreen.passProductDetails(
                                                                it!!.id ?: 1
                                                            )
                                                        )
                                                    },
                                                    product = it
                                                )
                                            }
                                        }
                                    }

                                    else -> {
                                        gridItems(
                                            productsByCategory.itemCount,
                                            nColumns = 2,
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            productsByCategory[it].let {
                                                SaleProduct(
                                                    sale_label = "",
                                                    width = 160.dp,
                                                    onClick = {
                                                        navController.navigate(
                                                            Screen.ProductDetailsScreen.passProductDetails(
                                                                it!!.id ?: 1
                                                            )
                                                        )
                                                    },
                                                    product = it
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    AnimatedVisibility(visible = !visible) {
                        LazyColumn(content = {
                            if (search.value.text.isNotEmpty() || filterOption != "По умолчанию") {
                                items(filteredProducts) {
                                    SaleProductList(navController, product = it)
                                }
                            } else {
                                when (productName) {
                                    -1 -> {
                                        items(products) {
                                            SaleProductList(navController, product = it)
                                        }
                                    }

                                    -2 -> {
                                        items(discProducts) {
                                            SaleProductList(navController, product = it)
                                        }
                                    }

                                    -3 -> {
                                        items(shopsItems) {
                                            SaleProductList(navController, product = it)
                                        }
                                    }

                                    -4 -> {
                                        items(advertisements) {
                                            SaleProductList(navController, product = it)
                                        }
                                    }

                                    else -> {
                                        items(productsByCategory) {
                                            SaleProductList(navController, product = it)
                                        }
                                    }
                                }

                            }
                        })
                    }
                }
            }

        }
}


@Composable
fun FilterBottomSheet(
    options: Map<String, String>,
    optionSelected: (String) -> Unit,
    categoryId: Int
) {
    val localFocusManager = LocalFocusManager.current
    Box(modifier = Modifier
        .height(350.dp)
        .fillMaxWidth()
        .pointerInput(Unit) {
            detectTapGestures(onTap = {
                localFocusManager.clearFocus()
            })
        }) {
        Divider(
            modifier = Modifier
                .padding(top = 10.dp)
                .width(70.dp)
                .height(8.dp)
                .clip(RoundedCornerShape(20.dp))
                .align(Alignment.TopCenter), color = Gray
        )
        Text(
            text = "Сортировать по",
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.metropolis_bold)),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 35.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
        ) {
            item {
                Sorting(options, optionSelected = { optionSelected(it) }, categoryId = categoryId)
            }
        }
    }
}


@Composable
fun Sorting(
    options: Map<String, String>,
    homeViewModel: HomeViewModel = hiltViewModel(),
    optionSelected: (String) -> Unit,
    categoryId: Int
) {
    var selectedOption by remember { mutableStateOf("") }
    Column(Modifier.fillMaxWidth()) {
        options.forEach { option ->
            Row(
                verticalAlignment = CenterVertically,
                modifier = Modifier
                    .requiredHeight(48.dp)
                    .fillMaxWidth()
                    .selectable(
                        selected = (selectedOption == option.key),
                        onClick = {
                            optionSelected(option.key)
                            selectedOption = option.key
                            if (categoryId >= 0) {
                                homeViewModel.updateData("category_id", categoryId - 1)
                            }
                            if (selectedOption != option.key && option.key != "По умолчанию") {
                                homeViewModel.updateData("sort", option.value)
                            } else if (option.key == "По умолчанию") {
                                homeViewModel._filteredData.remove("sort")
                            }
                            homeViewModel.getFilteredProducts()
                        }
                    )
                    .background(
                        color = if (selectedOption == option.key) {
                            Primary
                        } else {
                            Color.Transparent
                        }
                    )
            ) {
                Text(
                    text = option.key,
                    color = if (selectedOption == option.key) {
                        Color.White
                    } else {
                        Color.Black
                    },
                    fontWeight = if (selectedOption == option.key) {
                        FontWeight.Bold
                    } else {
                        FontWeight.Normal
                    },
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(start = 20.dp)
                )
            }
        }
    }
}



