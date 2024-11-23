package com.sahil.imageapp.presentation.components

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageVistaTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
    title: String = "Image App",
    onSearchClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append(title.split(" ").first())
                    }
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.secondary)) {
                        append(" ${title.split(" ").last()}")
                    }
                }, fontWeight = FontWeight.ExtraBold
            )
        }, actions = {
            IconButton(onClick = {
                Log.e("Search", "ImageVistaTopAppBar: works")
                onSearchClick()
            }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = null)
            }
        }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            scrolledContainerColor = MaterialTheme.colorScheme.background
        )
    )
}