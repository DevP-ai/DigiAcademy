package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.R
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.databinding.ActivityQuizGenerateBinding
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.utilities.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizGenerateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizGenerateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizGenerateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tbMain.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        setOptions()

        binding.btnGenerateQuestions.setOnClickListener {
            var category = binding.etProductCategory.text.toString()
            val difficulty = binding.etQuestionDifficulty.text.toString()
            if (category.isEmpty()) {
                Toast.makeText(this@QuizGenerateActivity, "Select category", Toast.LENGTH_SHORT)
                    .show()
            } else if (difficulty.isEmpty()) {
                Toast.makeText(this@QuizGenerateActivity, "Select difficulty level", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var categoryInt = 0
                when (category) {

                    "General Knowledge" -> categoryInt = 9
                    "Entertainment: Books" -> categoryInt = 10
                    "Entertainment: Film" -> categoryInt = 11
                    "Entertainment: Music" -> categoryInt = 12
                    "Science & Nature" -> categoryInt = 17
                    "Science: Computers" -> categoryInt = 18
                    "Mythology" -> categoryInt = 20
                    "Sports" -> categoryInt = 21
                    "Geography" -> categoryInt = 22
                    "History" -> categoryInt = 23
                    "Animals" -> categoryInt = 27
                    "Vehicles" -> categoryInt = 28
                }

                var difficultyString = ""
                when (difficulty) {
                    "Easy" -> difficultyString = "easy"
                    "Medium" -> difficultyString = "medium"
                    "Hard" -> difficultyString = "hard"
                }


                val intent = Intent(this@QuizGenerateActivity, QuizViewActivity::class.java)

                val bundle = Bundle()
                bundle.putInt("category", categoryInt)
                bundle.putString("difficulty", difficultyString)



                intent.putExtras(bundle)
                startActivity(intent)
                finish()
            }
        }

    }

    private fun setOptions() {
        val category = ArrayAdapter(
            this@QuizGenerateActivity,
            R.layout.showing_states_layout,
            Utils.listOfCategory
        )
        val difficulty = ArrayAdapter(
            this@QuizGenerateActivity,
            R.layout.showing_states_layout,
            Utils.listDifficulty
        )

        binding.apply {
            etProductCategory.setAdapter(category)
            etQuestionDifficulty.setAdapter(difficulty)
        }
    }
}