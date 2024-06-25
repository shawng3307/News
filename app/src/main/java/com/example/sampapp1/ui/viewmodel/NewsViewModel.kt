package com.example.sampapp1.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.sampapp1.data.model.NewsArticle
import com.example.sampapp1.data.model.NewsResponse
import com.example.sampapp1.data.repository.NewsRepository
import com.example.sampapp1.BuildConfig

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _articles = MutableLiveData<List<NewsArticle>>()
    val articles: LiveData<List<NewsArticle>> get() = _articles

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        fetchTopHeadlines()
    }

    fun fetchTopHeadlines() {
        viewModelScope.launch {
            try {
                val result = repository.getTopHeadlines("us", BuildConfig.NEWS_API_KEY)
                if (result.isSuccess) {
                    val newsResponse = result.getOrNull()
                    if (newsResponse != null && newsResponse.status == "ok") {
                        _articles.value = newsResponse.articles
                    } else {
                        _error.value = "API returned invalid response"
                        Log.e("NewsViewModel", "API error: ${newsResponse?.status}")
                    }
                } else {
                    val exception = result.exceptionOrNull()
                    _error.value = exception?.message ?: "An unknown error occurred"
                    Log.e("NewsViewModel", "Error fetching headlines", exception)
                }
            } catch (e: Exception) {
                _error.value = "An unexpected error occurred: ${e.message}"
                Log.e("NewsViewModel", "Exception in fetchTopHeadlines", e)
            }
        }
    }
}
