package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.datasource

import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.NewsResponse
import retrofit2.Response

interface NewsDataSource {
    suspend fun getNewsDataSource(country:String,category:String,query:String): Response<NewsResponse>
}