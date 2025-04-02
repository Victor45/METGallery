package com.example.metgallery.data.remote.dto

import com.example.metgallery.domain.model.Elements
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiElements(
    @Json(name = "total") val total : Int,
    @Json(name = "objectIDs") val objectIDs : Array<Int>
)

fun ApiElements.toElements() : Elements{
    return Elements(
        objectIDs = objectIDs
    )
}