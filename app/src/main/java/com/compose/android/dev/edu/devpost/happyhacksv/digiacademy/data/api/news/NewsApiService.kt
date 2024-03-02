package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.api.news

import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.news.NewsResponse
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.utilities.AppConstant.NEWS_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getNewsHeadLine(
        @Query("country")country:String,
        @Query("category")category:String,
        @Query("q")query:String,
        @Query("apiKey")apiKey:String=NEWS_API_KEY
    ): Response<NewsResponse>
}