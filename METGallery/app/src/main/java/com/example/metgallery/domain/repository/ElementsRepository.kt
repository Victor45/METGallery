package com.example.metgallery.domain.repository

import com.example.metgallery.domain.model.Elements
import com.example.metgallery.domain.model.ElemDetails

interface ElementsRepository{
    suspend fun getElements(query: String): Elements
    suspend fun getElemDetails(objectId: Int): ElemDetails
}
