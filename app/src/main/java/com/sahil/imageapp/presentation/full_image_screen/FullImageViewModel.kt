package com.sahil.imageapp.presentation.full_image_screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.sahil.imageapp.domain.model.UnSplashImage
import com.sahil.imageapp.domain.repository.Downloader
import com.sahil.imageapp.domain.repository.ImageRepository
import com.sahil.imageapp.presentation.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FullImageViewModel @Inject constructor(
    private val repository: ImageRepository,
    private val downloader: Downloader,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val imageId = savedStateHandle.toRoute<Routes.FullImageScreen>().imageId

    var image: UnSplashImage? by mutableStateOf(null)
        private set

    init {
        getImage()
    }

    private fun getImage() {
        viewModelScope.launch{
            try {
                val result = repository.getImage(imageId)
                image = result
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun downloadImage(url: String, title: String?) {
        viewModelScope.launch{
            try {
                downloader.downloadFile(url,title)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}