package com.example.metgallery.domain.model

import com.example.metgallery.data.local.FavoriteElem

data class FavoriteElement(
    val objectId: Int
)

fun FavoriteElement.toFavoriteElem(): FavoriteElem{
    return FavoriteElem(
        objectId = objectId
    )
}
