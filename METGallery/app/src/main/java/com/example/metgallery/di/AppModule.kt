package com.example.metgallery.di


import androidx.room.Room
import com.example.metgallery.data.local.FavoriteDatabase
import com.example.metgallery.domain.repository.ElementsRepository
import com.example.metgallery.data.repository.ElementsRepositoryImpl
import com.example.metgallery.data.repository.FavoriteRepository
import com.example.metgallery.domain.repository.RemoteElemDataSource
import com.example.metgallery.data.repository.RemoteElemDataSourceImpl
import com.example.metgallery.ui.elements.SearchViewModel
import com.example.metgallery.ui.elemDetails.DetailsViewModel
import com.example.metgallery.ui.favorite.FavoriteViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val AppModule = module {
    viewModelOf(::SearchViewModel)
    viewModelOf(::DetailsViewModel)
    viewModelOf(::FavoriteViewModel)

    singleOf(::ElementsRepositoryImpl) bind ElementsRepository::class
    singleOf(::RemoteElemDataSourceImpl) bind RemoteElemDataSource::class
    singleOf(::FavoriteRepository)


    single {
        Room.databaseBuilder(
            androidApplication(),
            FavoriteDatabase::class.java,
            "favorite_database"
        ).build()
    }

    single { get<FavoriteDatabase>().favoriteElem() }
}