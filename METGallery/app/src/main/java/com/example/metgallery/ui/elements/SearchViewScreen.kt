package com.example.metgallery.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.metgallery.domain.model.FavoriteElement
import com.example.metgallery.ui.favorite.FavoriteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchViewScreen(
    onResultClick: (Int) -> Unit,
    onFavTextClick: () -> Unit,
    viewModel: SearchViewModel = koinViewModel(),
    favViewModel: FavoriteViewModel = koinViewModel()
){
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()
    val searchText by viewModel.searchText.collectAsStateWithLifecycle()
    val favorites by viewModel.favorites.collectAsStateWithLifecycle()
    val favoritesIds = favorites.map { it.objectId }

    SearchViewScreenPrivate(
        searchResults = searchResults,
        onResultClick = onResultClick,
        searchText = searchText,
        onSearchTextChange = viewModel::onSearchTextChange,
        onFavTextClick = onFavTextClick,
        favoritesIds = favoritesIds,
        favViewModel = favViewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchViewScreenPrivate(
    searchResults: Array<Int>,
    onResultClick: (Int) -> Unit,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onFavTextClick: () -> Unit,
    favoritesIds: List<Int>,
    favViewModel: FavoriteViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Metropolitan Museum of Art",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = Color.Red,
            fontWeight = FontWeight.Bold,

        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { (Text(text = "Search")) },
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Favorites",
            fontSize = 20.sp,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { onFavTextClick() },
            textAlign = TextAlign.Center
        )
        if (searchResults.isEmpty()){
            Text(
                text = "No objects found",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Center
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(searchResults.toList()) { id ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$id",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onResultClick(id) },
                        color = Color.Black
                    )
                    Icon(
                        imageVector = if (favoritesIds.contains(id)) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        tint = Color.Red,
                        contentDescription = "Favorite",
                        modifier = Modifier
                            .clickable {
                                if (favoritesIds.contains(id)) {
                                    favViewModel.deleteFavorite(id)
                                } else {
                                    favViewModel.addFavorite(FavoriteElement(objectId = id))
                                }
                            }
                    )
                }
            }
        }
    }
}