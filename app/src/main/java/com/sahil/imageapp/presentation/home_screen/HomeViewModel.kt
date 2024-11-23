package com.sahil.imageapp.presentation.home_screen

import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sahil.imageapp.domain.model.UnSplashImage
import com.sahil.imageapp.domain.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ImageRepository) : ViewModel() {

    init {
        getImages()
    }

    var images: List<UnSplashImage> by mutableStateOf(emptyList())
        private set

    private fun getImages() {
        viewModelScope.launch {
            val result = repository.getEditorialFeedImages()
            images = result
        }
    }
}