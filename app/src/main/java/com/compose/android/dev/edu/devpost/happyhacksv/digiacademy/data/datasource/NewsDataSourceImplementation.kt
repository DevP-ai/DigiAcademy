package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.datasource

import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.api.NewsApiService
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class NewsDataSourceImplementation @Inject constructor(
    private val newsApiService: NewsApiService
):NewsDataSource {
    override suspend fun getNewsDataSource(
        country: String,
        category: String,
        query: String
    ): Response<NewsResponse> {
        return  newsApiService.getNewsHeadLine(country,category,query)
    }

}