package com.example.mycityapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycityapp.data.Recommendation
import com.example.mycityapp.data.DataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecommendationListViewModel : ViewModel() {

    private val dataRepository = DataRepository()

    private val _recommendations = MutableStateFlow<List<Recommendation>>(emptyList())
    val recommendations: StateFlow<List<Recommendation>> = _recommendations.asStateFlow()

    private val _categoryName = MutableStateFlow<String>("")
    val categoryName: StateFlow<String> = _categoryName.asStateFlow()

    fun loadRecommendationsForCategory(categoryId: Int) {
        viewModelScope.launch {
            val name = dataRepository.getCategoryNameByResourceId(categoryId)
            _categoryName.value = name
            _recommendations.value = dataRepository.loadRecommendations(name)
        }
    }
}