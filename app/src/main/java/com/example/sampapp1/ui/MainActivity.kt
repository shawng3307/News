package com.example.sampapp1.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampapp1.R
import com.example.sampapp1.data.repository.NewsRepository
import com.example.sampapp1.ui.adapter.NewsAdapter
import com.example.sampapp1.ui.theme.SampApp1Theme
import com.example.sampapp1.ui.viewmodel.NewsViewModel
import com.example.sampapp1.ui.viewmodel.NewsViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = NewsRepository()
        val viewModelFactory = NewsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        newsAdapter = NewsAdapter()
        recyclerView.adapter = newsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.articles.observe(this, Observer { articles ->
            newsAdapter.setArticles(articles)
        })

    }
}