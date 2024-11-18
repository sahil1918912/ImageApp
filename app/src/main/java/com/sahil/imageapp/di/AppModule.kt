package com.sahil.imageapp.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sahil.imageapp.data.remote.UnSplashApiService
import com.sahil.imageapp.data.util.Constants
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object AppModule {

    val contentType = "application/json".toMediaType()
    val json = Json { ignoreUnknownKeys = true}

    private val retrofit = Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory(contentType))
            .baseUrl(Constants.BASE_URL)
            .build()

    val retrofitService : UnSplashApiService by lazy {
        retrofit.create(UnSplashApiService::class.java)
    }
}