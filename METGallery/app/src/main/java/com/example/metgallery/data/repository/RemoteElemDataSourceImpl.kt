package com.example.metgallery.data.repository

import com.example.metgallery.data.remote.METApi
import com.example.metgallery.data.remote.dto.ApiElements
import com.example.metgallery.data.remote.dto.ApiElemDetails
import com.example.metgallery.data.remote.metApi
import com.example.metgallery.domain.repository.RemoteElemDataSource

class RemoteElemDataSourceImpl : RemoteElemDataSource {

    private val api: METApi = metApi

    override suspend fun getElements(query: String): ApiElements {
        if (query.isBlank()){
            return ApiElements(0, emptyArray())
        }
        val response = api.getElements(query)

        if(response.isSuccessful){
            val responseBody = response.body()
            if (responseBody != null)
            {
                return responseBody
            }
        }
        return ApiElements(0, emptyArray())
    }

    override suspend fun getElemDetails(objectId: Int): ApiElemDetails {
        val response = api.getElemDetails(objectId = objectId)
        if (response.isSuccessful) {
            val responseBody = response.body()

            if (responseBody != null)
            {
                return responseBody
            }
        }
        throw Exception("Response body is null")
    }
}