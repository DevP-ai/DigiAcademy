package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity

data class NewsResponse(
    val status:String?="",
    val totalResults:Int,
    val articles:List<Article>
)
