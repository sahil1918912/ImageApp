package com.sahil.imageapp.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sahil.imageapp.data.remote.UnSplashApiService
import com.sahil.imageapp.data.repository.AndroidImageDownloader
import com.sahil.imageapp.data.repository.ImageRepositoryImpl
import com.sahil.imageapp.data.util.Constants
import com.sahil.imageapp.domain.repository.Downloader
import com.sahil.imageapp.domain.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUnsplashApiService(): UnSplashApiService {

        val contentType = "application/json".toMediaType()
        val json = Json { ignoreUnknownKeys = true}

        val retrofit = Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory(contentType))
            .baseUrl(Constants.BASE_URL)
            .build()

        return retrofit.create(UnSplashApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideImageRepository(apiService: UnSplashApiService) : ImageRepository {
        return ImageRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideAndroidImageDownloader(
        @ApplicationContext context: Context
    ) : Downloader {
        return AndroidImageDownloader(context)
    }
}