package com.example.metgallery.data.repository

import com.example.metgallery.data.local.FavoriteDao
import com.example.metgallery.data.local.toFavoriteElement
import com.example.metgallery.domain.model.FavoriteElement
import com.example.metgallery.domain.model.toFavoriteElem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteRepository(private val favoriteDao: FavoriteDao) {

    val readAllData: Flow<List<FavoriteElement>> = favoriteDao.getAllFavorites().map {
        favoriteElems -> favoriteElems.map { it.toFavoriteElement() }
    }

    suspend fun addFavorite (favoriteElement: FavoriteElement){
        favoriteDao.addFavorite(favoriteElement.toFavoriteElem())
    }
    
    suspend fun deleteFavorite (id: Int){
        favoriteDao.deleteFavorite(id)
    }
}