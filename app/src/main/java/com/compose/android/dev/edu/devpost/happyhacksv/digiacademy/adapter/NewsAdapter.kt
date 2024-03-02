package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.adapter

import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.R
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.news.Article
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.databinding.NewsListBinding
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.activity.NewsDetailsActivity

class NewsAdapter(var newsList:List<Article>): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    inner class NewsViewHolder(val newsListBinding: NewsListBinding): RecyclerView.ViewHolder(newsListBinding.root){
        init{
            newsListBinding.root.setOnClickListener {
                val position=bindingAdapterPosition
                if(position!= RecyclerView.NO_POSITION){
                    val selectNews=newsList[position]
                    val intent= Intent(newsListBinding.root.context, NewsDetailsActivity::class.java)
                    intent.putExtra("news",selectNews as Parcelable)
                    newsListBinding.root.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(NewsListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return  newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        var data=newsList[position]
        holder.newsListBinding.textTitle.text=data.title
        if(data.urlToImage==null){
            holder.newsListBinding.imgHeadline.setImageResource(R.drawable.not_available)
        }else{
            Glide.with(holder.itemView)
                .load(data.urlToImage)
                .into(holder.newsListBinding.imgHeadline)
        }
    }


}