package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.room.QuizResult
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.databinding.ResultListBinding
import java.util.Locale

class ResultAdapter: RecyclerView.Adapter<ResultAdapter.ResultViewHolder>(){

    inner class ResultViewHolder(val binding: ResultListBinding): RecyclerView.ViewHolder(binding.root)

    private val diffUtil=object : DiffUtil.ItemCallback<QuizResult>(){
        override fun areItemsTheSame(oldItem: QuizResult, newItem: QuizResult): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: QuizResult, newItem: QuizResult): Boolean {
            return oldItem==newItem
        }

    }
    val differ= AsyncListDiffer(this,diffUtil)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(ResultListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val result=differ.currentList[position]
        holder.binding.resultView.text="${result.score.toString()}/10"
        holder.binding.difficultyLevel.text= result.difficulty?.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }
        holder.binding.categoryView.text=result.category
    }
}