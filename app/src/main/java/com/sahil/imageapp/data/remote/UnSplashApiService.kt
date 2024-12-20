package com.sahil.imageapp.data.remote

import com.sahil.imageapp.data.remote.dto.UnsplashImageDto
import com.sahil.imageapp.data.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface UnSplashApiService {

    @Headers("Authorization: Client-ID $API_KEY")
    @GET("/photos")
    suspend fun getEditorialFeedImages() : List<UnsplashImageDto>

    @Headers("Authorization: Client-ID $API_KEY")
    @GET("/photos/{id}")
    suspend fun getImage(
        @Path("id") imageId: String
    ): UnsplashImageDto
}