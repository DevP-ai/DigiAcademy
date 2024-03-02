package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.R
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.news.Article
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.databinding.ActivityNewsDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

@AndroidEntryPoint
class NewsDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedNews: Serializable? = intent.getSerializableExtra("news")
        if(selectedNews!=null){
            displayNewsDetails(selectedNews)

        }else{
            Toast.makeText(this,"Something wrong", Toast.LENGTH_SHORT).show()
        }

    }

    private fun displayNewsDetails(selectedNews: Serializable) {
        if(selectedNews is Article){
            binding.newsTitle.text=selectedNews.title
            binding.newsTime.text=selectedNews.publishedAt
            binding.newsDesc.text=selectedNews.description
            binding.newsDetails.text=selectedNews.content
            binding.newAuthor.text=selectedNews.author

            binding.readMore.setOnClickListener {
                selectedNews.url?.let {url->
                    openInBrowser(url)
                }
            }
            if(selectedNews.urlToImage==null){
                binding.newsImage.setImageResource(R.drawable.not_available)
            }else{
                Glide.with(this).load(selectedNews.urlToImage).into(binding.newsImage)
            }
        }
    }
    private fun openInBrowser(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}