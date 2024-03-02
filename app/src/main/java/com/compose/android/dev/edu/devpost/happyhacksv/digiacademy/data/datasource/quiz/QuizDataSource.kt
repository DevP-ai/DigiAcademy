package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.datasource.quiz

import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.quiz.Questions
import retrofit2.Response

interface QuizDataSource {
    suspend fun getQuizDataSource(amount:Int,category:Int,difficulty:String,type:String): Response<Questions>

}