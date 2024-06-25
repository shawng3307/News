package com.example.sampapp1.data.repository

import android.util.Log
import com.example.sampapp1.data.model.NewsResponse
import com.example.sampapp1.data.network.RetrofitInstance
import retrofit2.Response

class NewsRepository {
    private val apiService = RetrofitInstance.api

    suspend fun getTopHeadlines(country: String, apiKey: String): Result<NewsResponse> {
        return try {
            val response = apiService.getTopHeadlines(country, apiKey)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("API call failed with code ${response.code()}: ${response.errorBody()?.string()}"))
            }
        } catch (e: Exception) {
            Log.e("NewsRepository", "error fetching", e)
            Result.failure(e)
        }
    }
}