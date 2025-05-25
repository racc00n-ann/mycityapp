package com.example.mycityapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycityapp.data.Category
import com.example.mycityapp.data.DataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SharedCategoriesViewModel : ViewModel() {
    private val repository = DataRepository()

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories.asStateFlow()

    private val _selectedCategory = MutableStateFlow<Category?>(null)
    val selectedCategory: StateFlow<Category?> = _selectedCategory.asStateFlow()

    init { loadCategories() }

    private fun loadCategories() {
        viewModelScope.launch {
            _categories.value = repository.loadCategories()
            _selectedCategory.value = _categories.value.firstOrNull()
        }
    }

    fun setSelectedCategory(category: Category) {
        _selectedCategory.value = category
    }

    
}