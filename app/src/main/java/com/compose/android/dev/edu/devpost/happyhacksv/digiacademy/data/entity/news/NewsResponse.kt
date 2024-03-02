package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.news

import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.news.Article

data class NewsResponse(
    val status:String?="",
    val totalResults:Int,
    val articles:List<Article>
)
