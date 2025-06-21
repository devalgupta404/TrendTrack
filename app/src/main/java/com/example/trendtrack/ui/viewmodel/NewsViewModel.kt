package com.example.trendtrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trendtrack.data.model.Article
import com.example.trendtrack.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    init {
        fetchNews()
    }
    fun fetchNews() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val result = repository.getNews()
                _articles.value = result
            } catch (e: Exception) {
                _error.value = e.message ?: "An error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }
    fun searchNews(query: String){
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val result = repository.searchNews(query)
                _articles.value = result
            } catch (e: Exception) {
                _error.value = e.message ?: "An error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }
    fun updateArticle(article: Article) {
        val currentArticles = _articles.value.toMutableList()
        val index = currentArticles.indexOfFirst { it.url == article.url }
        if (index != -1){
            currentArticles[index] = article
            _articles.value = currentArticles
        }
    }
    fun refresh() {
        fetchNews()
    }
} 