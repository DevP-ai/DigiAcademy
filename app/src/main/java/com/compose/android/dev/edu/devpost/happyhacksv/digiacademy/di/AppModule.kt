package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.di

import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.utilities.AppConstant.NEWS_API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(Singleton::class)
class AppModule {
    @Singleton
    @Provides
    @Named("newsRetrofit")
    fun provideNewsRetrofit(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(NEWS_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}