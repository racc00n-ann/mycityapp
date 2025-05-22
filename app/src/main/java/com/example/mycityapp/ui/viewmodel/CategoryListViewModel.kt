package com.example.mycityapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycityapp.data.Category
import com.example.mycityapp.data.DataRepository
import kotlinx.coroutines.launch

class CategoryListViewModel : ViewModel() {

    private val dataRepository = DataRepository()

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            _categories.value = dataRepository.loadCategories()
        }
    }
}