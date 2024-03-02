package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.repository

import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.datasource.news.NewsDataSource
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.news.NewsResponse
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.utilities.Status
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.utilities.Result
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsDataSource: NewsDataSource
) {
    suspend fun getNewsFromDataSource(country:String,category:String,query:String):Result<NewsResponse>{
        return try {
            val response=newsDataSource.getNewsDataSource(country,category,query)

            if(response.isSuccessful){
                Result(Status.SUCCESS,response.body(),null)
            }else{
                Result(Status.ERROR,null,null)
            }

        }catch (e:Exception){
            Result(Status.ERROR,null,null)
        }
    }
}