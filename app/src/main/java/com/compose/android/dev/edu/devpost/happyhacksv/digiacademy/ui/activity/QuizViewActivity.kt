package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.R
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.databinding.ActivityQuizViewBinding
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.viewmodel.quiz.QuizViewModel
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.utilities.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizViewBinding

    private var category: Int? = null
    private lateinit var difficulty: String
    private lateinit var type: String
    private var numberOfQuestion: Int? = 10

    private var currentQuestionIndex = 0;
    private var selectedAlternativeIndex = -1;
    private var isAnswerChecked = false;
    private var totalScore = 0;

    private var tvAlternatives: ArrayList<TextView>? = null

    private var correctIndex = 0;


    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityQuizViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        tvAlternatives = arrayListOf(
            findViewById(R.id.optionOne),
            findViewById(R.id.optionTwo),
            findViewById(R.id.optionThree),
            findViewById(R.id.optionFour),
        )

        val extras: Bundle? = intent.extras
        if (extras != null) {
            category = extras.getInt("category")
            difficulty = extras.getString("difficulty").toString()
        }
        type="multiple"

        quizViewModel.getQuiz(numberOfQuestion!!, category!!, difficulty, type)


        quizViewModel.quiz.observe(this) { event ->
            event.getContentIfNotHandle()?.let { result ->
                when (result.status) {

                    Status.LOADING -> {

                    }

                    Status.SUCCESS -> {
                        updateQuestions()
                        binding.btnSubmit.setOnClickListener {
                            binding.progressBar?.progress = currentQuestionIndex + 1
                            if (!isAnswerChecked) {
                                val anyAnsIsCheck = selectedAlternativeIndex != -1
                                if (!anyAnsIsCheck) {
                                    Toast.makeText(
                                        this,
                                        "Please select any option",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    val correctOption = quizViewModel.correctOption
                                    for (i in 0 until tvAlternatives!!.size) {
                                        if (correctOption == tvAlternatives!![i].text.toString()) {
                                            correctIndex = i
                                        }
                                    }
                                    if (selectedAlternativeIndex == correctIndex) {
                                        answerView(
                                            tvAlternatives!![selectedAlternativeIndex],
                                            R.drawable.correct_option_border_bg
                                        )
                                        totalScore++;
                                    } else {
                                        answerView(
                                            tvAlternatives!![selectedAlternativeIndex],
                                            R.drawable.wrong_option_border_bg
                                        )
                                        answerView(
                                            tvAlternatives!![correctIndex],
                                            R.drawable.correct_option_border_bg
                                        )
                                    }
                                    isAnswerChecked = true
                                    binding.btnSubmit.text =
                                        if (currentQuestionIndex == (numberOfQuestion?.minus(1))) "FINISH" else "NEXT"
                                    selectedAlternativeIndex = -1
                                }
//
                            } else {
                                if (currentQuestionIndex < numberOfQuestion!! - 1) {
                                    currentQuestionIndex++
                                    quizViewModel.nextQuestion()
                                    updateQuestions()
                                }
                                else{
                                    val intent= Intent(this,ResultActivity::class.java)
                                    intent.putExtra("score",totalScore)
                                    intent.putExtra("category",category)
                                    intent.putExtra("difficulty",difficulty)
                                    startActivity(intent)
                                    finish()
                                }

                                isAnswerChecked = false
                            }
                        }
                    }

                    Status.ERROR -> {

                    }
                }
            }
        }



    }

    private fun updateQuestions() {

        defaultAlternativesView()

        val options = listOf(
            quizViewModel.currentOptions?.get(0),
            quizViewModel.currentOptions?.get(1),
            quizViewModel.currentOptions?.get(2),
            quizViewModel.currentOptions?.get(3)
        )

        val randomNumbers = listOf(0, 1, 2, 3).shuffled().take(4)

        binding.tvQuestion.text = quizViewModel.currentQuestionText
        binding.optionOne.text = options[randomNumbers[0]]
        binding.optionTwo.text = options[randomNumbers[1]]
        binding.optionThree.text = options[randomNumbers[2]]
        binding.optionFour.text = options[randomNumbers[3]]



        binding.tvProgress.text = "${currentQuestionIndex + 1}/${numberOfQuestion}"



        for (i in 0 until tvAlternatives!!.size) {
            tvAlternatives!![i].setOnClickListener {
                if (!isAnswerChecked) {
                    resetOptionBorders()
                    setOptionBorder(it as TextView)
                    selectedAlternativeIndex = i
                }
            }
        }

        binding.btnSubmit.text =
            if (currentQuestionIndex == (numberOfQuestion)) "FINISH" else "SUBMIT"
    }

    private fun setOptionBorder(option: TextView) {
        option.setBackgroundResource(R.drawable.selected_option_border_bg)
    }

    private fun resetOptionBorders() {
        for (option in tvAlternatives!!) {
            option.setBackgroundResource(R.drawable.default_option_border_bg)
        }
    }


    private fun answerView(view: TextView, drawableId: Int) {
        view.background = ContextCompat.getDrawable(
            this@QuizViewActivity,
            drawableId
        )
        tvAlternatives!![selectedAlternativeIndex].setTextColor(
            Color.parseColor("#FFFFFF")
        )
    }

    private fun defaultAlternativesView() {
        for (alternativeTv in tvAlternatives!!) {
            alternativeTv.typeface = Typeface.DEFAULT
            alternativeTv.setTextColor(Color.parseColor("#7A8089"))
            alternativeTv.background = ContextCompat.getDrawable(
                this@QuizViewActivity,
                R.drawable.default_option_border_bg
            )
        }
    }

}