package com.example.metgallery.ui

interface IUserScreen{
    suspend fun DetailsScreen(): Unit;
    suspend fun SearchViewScreen(): Unit;
    suspend fun FavoriteScreen(): Unit;
}