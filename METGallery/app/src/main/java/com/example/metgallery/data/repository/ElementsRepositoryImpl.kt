package com.example.metgallery.data.repository

import com.example.metgallery.data.remote.dto.toElemDetails
import com.example.metgallery.data.remote.dto.toElements
import com.example.metgallery.domain.model.Elements
import com.example.metgallery.domain.model.ElemDetails
import com.example.metgallery.domain.repository.ElementsRepository
import com.example.metgallery.domain.repository.RemoteElemDataSource

class ElementsRepositoryImpl(private val remoteElemDataSource: RemoteElemDataSource):
    ElementsRepository {
    override suspend fun getElements(query: String): Elements {
        return remoteElemDataSource.getElements(query = query).toElements()
    }

    override suspend fun getElemDetails(objectId: Int): ElemDetails {
        return remoteElemDataSource.getElemDetails(objectId = objectId).toElemDetails()
    }
}