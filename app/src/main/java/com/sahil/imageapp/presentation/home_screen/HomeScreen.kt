package com.sahil.imageapp.presentation.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sahil.imageapp.R
import com.sahil.imageapp.domain.model.UnSplashImage
import com.sahil.imageapp.presentation.components.ImageVerticalGrid
import com.sahil.imageapp.presentation.components.ImageVistaTopAppBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    scrollBehavior: TopAppBarScrollBehavior,
    images: List<UnSplashImage>,
    onImageClick: (String) -> Unit,
    onSearchClick: () -> Unit,
    onFabClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageVistaTopAppBar(
                scrollBehavior = scrollBehavior,
                onSearchClick = onSearchClick
            )
            ImageVerticalGrid(
                images = images,
                onImageClick = onImageClick
            )
        }

        FloatingActionButton(modifier = Modifier.align(Alignment.BottomEnd).padding(24.dp), onClick = { onFabClick }) {
            Icon(
                painter = painterResource(R.drawable.ic_save),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}