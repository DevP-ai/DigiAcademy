package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.di

import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.api.news.NewsApiService
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.api.quiz.QuizApiService
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.datasource.news.NewsDataSource
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.datasource.news.NewsDataSourceImplementation
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.datasource.quiz.QuizDataSource
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.data.datasource.quiz.QuizDataSourceImplement
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.repository.news.NewsRepository
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.repository.quiz.QuizRepository
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.utilities.AppConstant.NEWS_API_BASE_URL
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.utilities.AppConstant.QUIZ_API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    @Named("newsRetrofit")
    fun provideNewsRetrofit(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(NEWS_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named("quizRetrofit")
    fun provideQuizRetrofit():Retrofit{
        return  Retrofit.Builder()
            .baseUrl(QUIZ_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService( @Named("newsRetrofit") retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideQuizApiService(@Named("quizRetrofit") retrofit: Retrofit):QuizApiService{
        return retrofit.create(QuizApiService::class.java)
    }


    @Singleton
    @Provides
    fun provideDataSource(newsApiService: NewsApiService): NewsDataSource {
        return NewsDataSourceImplementation(newsApiService)
    }


    @Singleton
    @Provides
    fun provideQuizDataSource(quizApiService: QuizApiService): QuizDataSource {
        return QuizDataSourceImplement(quizApiService)
    }

    @Singleton
    @Provides
    fun provideNewsRepository(newsDataSource: NewsDataSource): NewsRepository {
        return NewsRepository(newsDataSource)
    }

    @Singleton
    @Provides
    fun provideQuizRepository(quizDataSource: QuizDataSource):QuizRepository{
        return QuizRepository(quizDataSource)
    }

}