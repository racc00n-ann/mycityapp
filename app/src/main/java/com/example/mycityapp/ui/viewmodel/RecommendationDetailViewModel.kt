package com.example.mycityapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycityapp.data.Recommendation
import com.example.mycityapp.data.DataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecommendationDetailViewModel : ViewModel() {

    private val dataRepository = DataRepository()

    private val _recommendation = MutableStateFlow<Recommendation?>(null)
    val recommendation: StateFlow<Recommendation?> = _recommendation.asStateFlow()

    fun loadRecommendation(recommendationId: Int) {
        viewModelScope.launch {
            _recommendation.value = dataRepository.getRecommendationByResourceId(recommendationId)
        }
    }
}