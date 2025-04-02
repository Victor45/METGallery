package com.example.metgallery.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: FavoriteElem)

    @Query("DELETE FROM favorites WHERE objectId = :id")
    suspend fun deleteFavorite(id: Int)

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<FavoriteElem>>
}