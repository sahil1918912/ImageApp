package com.sahil.imageapp.domain.repository

import com.sahil.imageapp.domain.model.UnSplashImage

interface ImageRepository {
    suspend fun getEditorialFeedImages(): List<UnSplashImage>

    suspend fun getImage(imageId: String): UnSplashImage
}