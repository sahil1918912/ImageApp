package com.sahil.imageapp.domain.model

data class UnSplashImage (
    val id: String,
    val imageUrlSmall: String,
    val imageUrlRegular: String,
    val imageUrlRow: String,
    val photographerName: String,
    val photographerUserName: String,
    val photographerProfileImageUrl: String,
    val photographerProfileImageLink: String,
    val width: Int,
    val height: Int,
    val description: String
)