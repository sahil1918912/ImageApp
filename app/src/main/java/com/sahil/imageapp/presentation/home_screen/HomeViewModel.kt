package com.sahil.imageapp.presentation.home_screen

import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sahil.imageapp.data.mapper.toDomainList
import com.sahil.imageapp.di.AppModule
import com.sahil.imageapp.domain.model.UnSplashImage
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    init {
        getImages()
    }

    var images: List<UnSplashImage> by mutableStateOf(emptyList())
    private set

    private fun getImages() {
        viewModelScope.launch {
            val result = AppModule.retrofitService.getEditorialFeedImages()
            images = result.toDomainList()
        }
    }
}