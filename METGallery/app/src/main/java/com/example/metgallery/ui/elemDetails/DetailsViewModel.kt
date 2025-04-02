package com.example.metgallery.ui.elemDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.metgallery.domain.model.ElemDetails
import com.example.metgallery.domain.repository.ElementsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel(private val elementsRepository: ElementsRepository, savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val elementId: Int = savedStateHandle.toRoute<ElemDetailsRoute>().elementId

    private val _details: MutableStateFlow<ElemDetails> = MutableStateFlow(ElemDetails())
    val details: StateFlow<ElemDetails> = _details.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _details.update {
                try {
                    elementsRepository.getElemDetails(objectId = elementId) ?: ElemDetails()
                } catch (e: Exception) {
                    ElemDetails()
                }
            }
        }
    }
}