package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.repository.quiz

import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.datasource.quiz.QuizDataSource
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.quiz.Questions
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.utilities.Status
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.utilities.Result
import javax.inject.Inject

class QuizRepository @Inject constructor(
    private val quizDataSource: QuizDataSource
) {

    suspend fun getQuizFromDataSource(amount: Int,category: Int,difficulty: String,type: String):Result<Questions>{

        return  try {
            val response=quizDataSource.getQuizDataSource(amount,category,difficulty,type)
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