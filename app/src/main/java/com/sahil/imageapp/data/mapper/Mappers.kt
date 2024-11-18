package com.sahil.imageapp.data.mapper

import com.sahil.imageapp.data.remote.dto.UnsplashImageDto
import com.sahil.imageapp.domain.model.UnSplashImage

fun UnsplashImageDto.toDomainModel(): UnSplashImage {
    return UnSplashImage(
        id = this.id,
        imageUrlSmall = this.urls.small,
        imageUrlRegular = this.urls.regular,
        imageUrlRow = this.urls.raw,
        photographerName = this.user.name,
        photographerUserName = this.user.username,
        photographerProfileImageUrl = this.user.links.photos,
        photographerProfileImageLink = this.user.links.html,
        width = this.width,
        height = this.height,
        description = this.description.toString()
    )
}

fun List<UnsplashImageDto>.toDomainList(): List<UnSplashImage> {
    return this.map { it.toDomainModel() }
}