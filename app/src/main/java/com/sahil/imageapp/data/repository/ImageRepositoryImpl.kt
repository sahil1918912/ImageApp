package com.sahil.imageapp.data.repository

import com.sahil.imageapp.data.mapper.toDomainModel
import com.sahil.imageapp.data.mapper.toDomainModelList
import com.sahil.imageapp.data.remote.UnSplashApiService
import com.sahil.imageapp.domain.model.UnSplashImage
import com.sahil.imageapp.domain.repository.ImageRepository

class ImageRepositoryImpl(private val unSplashApi: UnSplashApiService) : ImageRepository {
    override suspend fun getEditorialFeedImages(): List<UnSplashImage> {
        return unSplashApi.getEditorialFeedImages().toDomainModelList()
    }

    override suspend fun getImage(imageId: String): UnSplashImage {
        return unSplashApi.getImage(imageId).toDomainModel()
    }
}