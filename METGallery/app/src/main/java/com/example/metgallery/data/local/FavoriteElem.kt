package com.example.metgallery.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.metgallery.domain.model.FavoriteElement

@Entity(tableName = "favorites")
data class FavoriteElem (
    @PrimaryKey(autoGenerate = true)
    val objectId: Int
)

fun FavoriteElem.toFavoriteElement() : FavoriteElement {
    return FavoriteElement(
        objectId = objectId
    )
}


