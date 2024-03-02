package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.datasource.news

import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.news.NewsResponse
import retrofit2.Response

interface NewsDataSource {
    suspend fun getNewsDataSource(country:String,category:String,query:String): Response<NewsResponse>
}