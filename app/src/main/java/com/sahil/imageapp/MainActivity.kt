package com.sahil.imageapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sahil.imageapp.presentation.home_screen.HomeScreen
import com.sahil.imageapp.presentation.home_screen.HomeViewModel
import com.sahil.imageapp.presentation.theme.ImageAppTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImageAppTheme {
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                val viewModel = viewModel<HomeViewModel>()
                Scaffold(modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection)) {
                    HomeScreen(
                        scrollBehavior = scrollBehavior,
                        images = viewModel.images,
                        onImageClick = {},
                        onSearchClick = {},
                        onFabClick = {}
                    )
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImageAppTheme {

    }
}