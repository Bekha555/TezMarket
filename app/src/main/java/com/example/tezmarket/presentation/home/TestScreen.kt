package com.example.tezmarket.presentation.home

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.largeTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen() {
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
   Scaffold(
       modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
       topBar = {
           LargeTopAppBar(
               title = { Text("Home", fontWeight = FontWeight.Bold) },
               navigationIcon = {
                   IconButton(onClick = { }) {
                       Icon(Icons.Default.Menu, contentDescription = "menu")
                   }
               },
               actions = {
                   IconButton(onClick = {  }) {
                       Icon(Icons.Default.Search, contentDescription = "search")
                   }
               },
               scrollBehavior = scrollBehavior,
               colors = largeTopAppBarColors(titleContentColor = Black, containerColor = Transparent, scrolledContainerColor = Transparent)
           )
       }
   ) {
       Column(modifier = Modifier
           .fillMaxSize()
           .verticalScroll(state = rememberScrollState()))
       {
       repeat(50){
           Text(text = "Hola")
       }

       }

   }
}