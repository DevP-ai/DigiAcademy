package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.viewmodel.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.quiz.Questions
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.quiz.Results
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.repository.quiz.QuizRepository
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.utilities.Events
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.utilities.Status
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.utilities.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository
):ViewModel(){

    private val _quiz = MutableLiveData<Events<Result<Questions>>>()
    val quiz: LiveData<Events<Result<Questions>>> = _quiz

    private var currentQuestionIndex=0
    private var currentQuestion: Results?=null

    val currentQuestionText:String?
        get() = currentQuestion?.question

    val currentOptions:List<String>?
        get() = currentQuestion?.incorrect_answers?.plus(currentQuestion?.correct_answer.toString())
    val correctOption:String
        get() = currentQuestion!!.correct_answer

    fun getQuiz(amount: Int,category: Int,difficulty: String,type: String)=
        viewModelScope.launch (Dispatchers.IO){
            _quiz.postValue(Events(Result(Status.LOADING,null,null)))
            val result=quizRepository.getQuizFromDataSource(amount,category,difficulty,type)
            _quiz.postValue(Events(result))

            if(result.status==Status.SUCCESS){
                result.data?.results?.let {questions->
                    currentQuestionIndex=0
                    currentQuestion=questions[currentQuestionIndex]
                }
            }
        }

    fun nextQuestion(){
        currentQuestionIndex++
        currentQuestion = quiz.value?.peekContent()?.data?.results?.getOrNull(currentQuestionIndex)
    }
}