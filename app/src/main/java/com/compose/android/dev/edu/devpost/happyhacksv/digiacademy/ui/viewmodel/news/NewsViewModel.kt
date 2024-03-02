package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.viewmodel.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.entity.news.NewsResponse
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.repository.news.NewsRepository
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.utilities.Events
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.utilities.Result
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.utilities.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
):ViewModel(){
    private val _news= MutableLiveData<Events<Result<NewsResponse>>>()
    val news: LiveData<Events<Result<NewsResponse>>> =_news

    fun getNews(country:String, category:String, query: String)=viewModelScope.launch (Dispatchers.IO){
        _news.postValue(Events(Result(Status.LOADING,null,null)))
        _news.postValue(Events(newsRepository.getNewsFromDataSource(country,category,query)))
    }
}