package com.example.metgallery.data.local


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteElem::class],
    version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteElem(): FavoriteDao
}