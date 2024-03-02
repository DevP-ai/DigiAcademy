package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.datasource.quiz

import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.api.quiz.QuizApiService
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.quiz.Questions
import retrofit2.Response
import javax.inject.Inject

class QuizDataSourceImplement @Inject constructor(
    private val quizApiService: QuizApiService
):QuizDataSource{
    override suspend fun getQuizDataSource(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): Response<Questions> {
        return  quizApiService.getAllQuestion(amount,category,difficulty,type)
    }
}