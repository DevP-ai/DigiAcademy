package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.R
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.room.QuizResult
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.databinding.ActivityResultBinding
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.viewmodel.quiz.ResultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultActivity : AppCompatActivity() {

    private lateinit var binding:ActivityResultBinding

    private val resultViewModel: ResultViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val score=intent.getIntExtra("score",0)
        val category=intent.getIntExtra("category",0)
        val difficulty=intent.getStringExtra("difficulty")

        var categoryString:String?=""
        when (category) {

            9->categoryString ="General Knowledge"
            10->categoryString ="Entertainment: Books"
            11->categoryString ="Entertainment: Film"
            12->categoryString ="Entertainment: Music"
            17->categoryString ="Science & Nature"
            18 ->categoryString ="Science: Computers"
            20->categoryString ="Mythology"
            21->categoryString ="Sports"
            22->categoryString ="Geography"
            23->categoryString ="History"
            27->categoryString ="Animals"
            28->categoryString ="Vehicles"
        }

        binding.scoreTv.text="Your score is $score out of 10"

        resultViewModel.insertQuizResult(QuizResult(score=score, category = categoryString, difficulty = difficulty))

        binding.btnRestart.setOnClickListener {
            startActivity(Intent(this,QuizGenerateActivity::class.java))
            finish()
        }
    }
}