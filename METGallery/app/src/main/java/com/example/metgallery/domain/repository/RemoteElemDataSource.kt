package com.example.metgallery.domain.repository

import com.example.metgallery.data.remote.dto.ApiElements
import com.example.metgallery.data.remote.dto.ApiElemDetails

interface RemoteElemDataSource{
    suspend fun getElements(query: String): ApiElements
    suspend fun getElemDetails(objectId: Int): ApiElemDetails
}
