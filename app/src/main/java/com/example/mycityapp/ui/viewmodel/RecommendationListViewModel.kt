package com.example.mycityapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycityapp.data.Recommendation
import com.example.mycityapp.data.DataRepository
import kotlinx.coroutines.launch

class RecommendationListViewModel : ViewModel() {

    private val dataRepository = DataRepository()

    private val _recommendations = MutableLiveData<List<Recommendation>>()
    val recommendations: LiveData<List<Recommendation>> = _recommendations

    private val _categoryName = MutableLiveData<String>()
    val categoryName: LiveData<String> = _categoryName

    fun loadRecommendationsForCategory(categoryId: Int) {
        viewModelScope.launch {
            val name = dataRepository.getCategoryNameByResourceId(categoryId)
            _categoryName.value = name
            _recommendations.value = dataRepository.loadRecommendations(name)
        }
    }
}