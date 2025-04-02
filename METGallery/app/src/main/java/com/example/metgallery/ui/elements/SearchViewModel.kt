package com.example.metgallery.ui.elements

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metgallery.data.repository.FavoriteRepository
import com.example.metgallery.domain.model.FavoriteElement
import com.example.metgallery.domain.repository.ElementsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class SearchViewModel(
    private val elementsRepository : ElementsRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _searchResults = MutableStateFlow<Array<Int>>(emptyArray())
    val searchResults = _searchResults.asStateFlow()

    private val _favorites = MutableStateFlow<List<FavoriteElement>>(emptyList())
    val favorites = _favorites.asStateFlow()

    fun onSearchTextChange(text: String){
        _searchText.value = text
    }

    init{
        loadData()
    }

    private fun loadData() {

        viewModelScope.launch {
            favoriteRepository.readAllData.collect { data ->
                _favorites.value = data
            }
        }

        viewModelScope.launch {
            _searchText
                .debounce(300)
                .distinctUntilChanged()
                .collectLatest { query ->
                    _searchResults.value = try {
                        elementsRepository.getElements(query).objectIDs
                    } catch (e: Exception) {
                        emptyArray()
                    }
                }
        }
    }
}