package com.sahil.imageapp.domain.model

data class UnSplashImage (
    val id: String,
    val imageUrlSmall: String,
    val imageUrlRegular: String,
    val imageUrlRow: String,
    val photographerName: String,
    val photographerUserName: String,
    val photographerProfileImgUrl: String,
    val photographerProfileLink: String,
    val width: Int,
    val height: Int,
    val description: String?
)