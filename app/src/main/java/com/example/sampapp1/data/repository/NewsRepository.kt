package com.example.sampapp1.data.repository

import android.util.Log
import com.example.sampapp1.data.model.NewsResponse
import com.example.sampapp1.data.network.RetrofitInstance
import retrofit2.Response

class NewsRepository {
    private val apiService = RetrofitInstance.api

    suspend fun getTopHeadlines(country: String, apiKey: String): NewsResponse {
        Log.d("NewsRepository", apiKey)
        return apiService.getTopHeadlines(country, apiKey)
    }
}