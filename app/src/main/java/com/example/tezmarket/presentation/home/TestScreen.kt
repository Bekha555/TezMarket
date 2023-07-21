package com.example.tezmarket.presentation.home

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
//    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
//    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
//   Scaffold(
//       modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
//       topBar = {
//           LargeTopAppBar(
//               title = { Text("Home", fontWeight = FontWeight.Bold) },
//               navigationIcon = {
//                   IconButton(onClick = { }) {
//                       Icon(Icons.Default.Menu, contentDescription = "menu")
//                   }
//               },
//               actions = {
//                   IconButton(onClick = {  }) {
//                       Icon(Icons.Default.Search, contentDescription = "search")
//                   }
//               },
//               scrollBehavior = scrollBehavior,
//               colors = largeTopAppBarColors(titleContentColor = Black, containerColor = Transparent, scrolledContainerColor = Transparent)
//           )
//       }
//   ) {
//       Column(modifier = Modifier
//           .fillMaxSize()
//           .verticalScroll(state = rememberScrollState()))
//       {
//       repeat(50){
//           Text(text = "Hola")
//       }
//
//       }
//
//   }
}