package com.example.metgallery.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metgallery.data.repository.FavoriteRepository
import com.example.metgallery.domain.model.FavoriteElement
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {

    private val _favorites = MutableStateFlow<List<FavoriteElement>>(emptyList())
    val favorites = _favorites.asStateFlow()

    init {
        getFavorites()
    }

    private fun getFavorites(){
        viewModelScope.launch {
            favoriteRepository.readAllData.collect{ data -> _favorites.value = data }
        }
    }

    fun addFavorite(favoriteElement: FavoriteElement) {
        viewModelScope.launch {
            favoriteRepository.addFavorite(favoriteElement)
        }
    }

    fun deleteFavorite(id: Int) {
        viewModelScope.launch {
            favoriteRepository.deleteFavorite(id)
        }
    }
}