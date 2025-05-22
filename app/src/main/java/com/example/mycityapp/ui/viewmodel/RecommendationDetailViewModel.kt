package com.example.mycityapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycityapp.data.Recommendation
import com.example.mycityapp.data.DataRepository
import kotlinx.coroutines.launch

class RecommendationDetailViewModel : ViewModel() {

    private val dataRepository = DataRepository()

    private val _recommendation = MutableLiveData<Recommendation?>()
    val recommendation: LiveData<Recommendation?> = _recommendation

    fun loadRecommendation(recommendationId: Int) {
        viewModelScope.launch {
            _recommendation.value = dataRepository.getRecommendationByResourceId(recommendationId)
        }
    }
}