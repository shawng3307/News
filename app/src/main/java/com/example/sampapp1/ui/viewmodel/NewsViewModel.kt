package com.example.sampapp1.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.sampapp1.data.model.NewsArticle
import com.example.sampapp1.data.repository.NewsRepository
import com.example.sampapp1.BuildConfig

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _articles = MutableLiveData<List<NewsArticle>>()
    val articles: LiveData<List<NewsArticle>> get() = _articles

    init {
        viewModelScope.launch {
            try {
                val response = repository.getTopHeadlines("us", BuildConfig.NEWS_API_KEY)
                if (response.status == "ok") {
                    _articles.value = response.articles
                } else {
                    Log.d("NewsViewModel", "found else there")
                    Log.e("NewsViewModel", "Error: ${response}")
                }
            } catch (e: Exception) {
                Log.d("NewsViewModel", "found else here")
                Log.e("NewsViewModel", "Exception: ${e.message}")
            }
        }
    }
}