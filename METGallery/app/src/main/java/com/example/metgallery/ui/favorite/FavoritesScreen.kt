package com.example.metgallery.ui.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.metgallery.domain.model.FavoriteElement
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoritesScreen(
    onFavoriteClick: (Int) -> Unit,
    onUpClick: () -> Unit,
    viewModel: FavoriteViewModel = koinViewModel()
){
    val favorites by viewModel.favorites.collectAsStateWithLifecycle()
    FavoritesScreenPrivate(
        favorites = favorites,
        onFavoriteClick = onFavoriteClick,
        onUpClick = onUpClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FavoritesScreenPrivate(
    favorites: List<FavoriteElement>,
    onFavoriteClick: (Int) -> Unit,
    onUpClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = onUpClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                title = { Text(text = "Favorites") },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(favorites) { elem ->
                Text(
                    text = "${elem.objectId}",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .clickable { onFavoriteClick(elem.objectId) },
                )
            }
        }
    }
}