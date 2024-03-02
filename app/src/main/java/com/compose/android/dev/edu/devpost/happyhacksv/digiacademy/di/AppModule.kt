package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.di

import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.api.news.NewsApiService
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.datasource.news.NewsDataSource
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.datasource.news.NewsDataSourceImplementation
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


    @Singleton
    @Provides
    fun provideApiService( @Named("newsRetrofit") retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDataSource(newsApiService: NewsApiService): NewsDataSource {
        return NewsDataSourceImplementation(newsApiService)
    }


}