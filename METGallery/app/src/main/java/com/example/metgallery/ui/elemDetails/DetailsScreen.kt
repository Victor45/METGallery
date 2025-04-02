package com.example.metgallery.ui.elemDetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.metgallery.domain.model.ElemDetails
import com.example.metgallery.domain.model.FavoriteElement
import com.example.metgallery.ui.favorite.FavoriteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    onUpClick: () -> Unit,
    viewModel: DetailsViewModel = koinViewModel(),
    favViewModel: FavoriteViewModel = koinViewModel()
) {
    val details by viewModel.details.collectAsStateWithLifecycle()
    val favorites by favViewModel.favorites.collectAsStateWithLifecycle()
    val favoritesIds = favorites.map { it.objectId }

    DetailsScreen(
        details = details,
        onUpClick = onUpClick,
        favViewModel = favViewModel,
        favoritesIds = favoritesIds
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailsScreen(
    details: ElemDetails,
    onUpClick: () -> Unit,
    favViewModel: FavoriteViewModel,
    favoritesIds: List<Int>
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
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = details.objectID.toString())
                        Icon(
                            imageVector = if (favoritesIds.contains(details.objectID))
                                Icons.Filled.Favorite
                            else
                                Icons.Filled.FavoriteBorder,
                            tint = Color.Red,
                            contentDescription = "Favorite",
                            modifier = Modifier.clickable {
                                if (favoritesIds.contains(details.objectID)) {
                                    favViewModel.deleteFavorite(details.objectID)
                                } else {
                                    favViewModel.addFavorite(FavoriteElement(objectId = details.objectID))
                                }
                            }
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (details.primaryImage.isNotBlank()) {
                AsyncImage(
                    model = details.primaryImage,
                    contentDescription = "Artwork Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            if (details.additionalImages.size < 3) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    details.additionalImages.forEach { imageUrl ->
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = "Additional Image",
                            modifier = Modifier
                                .size(150.dp)
                                .padding(8.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            } else {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    items(details.additionalImages) { imageUrl ->
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = "Additional Image",
                            modifier = Modifier
                                .size(150.dp)
                                .padding(8.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Text(text = "Object Name: ${details.objectName}", fontSize = 22.sp)
                Text(text = "Title: ${details.title}", fontSize = 22.sp)
                Text(text = "Period: ${details.period}", fontSize = 22.sp)
                Text(text = "Culture: ${details.culture}", fontSize = 22.sp)
                Text(text = "Accession Year: ${details.accessionYear}", fontSize = 22.sp)
                Text(text = "Department: ${details.department}", fontSize = 22.sp)
                Text(text = "Accession Number: ${details.accessionNumber}", fontSize = 22.sp)
                Text(text = "Is Public Domain: ${details.isPublicDomain}", fontSize = 22.sp)
                Text(text = "Is Highlight: ${details.isHighlight}", fontSize = 22.sp)
                Text(text = "Dynasty: ${details.dynasty}", fontSize = 22.sp)
                Text(text = "Reign: ${details.reign}", fontSize = 22.sp)
                Text(text = "Portfolio: ${details.portfolio}", fontSize = 22.sp)
                Text(text = "Artist Role: ${details.artistRole}", fontSize = 22.sp)
                Text(text = "Artist Prefix: ${details.artistPrefix}", fontSize = 22.sp)
                Text(text = "Artist Display Name: ${details.artistDisplayName}", fontSize = 22.sp)
                Text(text = "Artist Display Bio: ${details.artistDisplayBio}", fontSize = 22.sp)
                Text(text = "Artist Suffix: ${details.artistSuffix}", fontSize = 22.sp)
                Text(text = "Artist Alpha Sort: ${details.artistAlphaSort}", fontSize = 22.sp)
                Text(text = "Artist Nationality: ${details.artistNationality}", fontSize = 22.sp)
                Text(text = "Artist Begin Date: ${details.artistBeginDate}", fontSize = 22.sp)
                Text(text = "Artist End Date: ${details.artistEndDate}", fontSize = 22.sp)
                Text(text = "Artist Gender: ${details.artistGender}", fontSize = 22.sp)
                Text(text = "Medium: ${details.medium}", fontSize = 22.sp)
                Text(text = "Dimensions: ${details.dimensions}", fontSize = 22.sp)
                Text(text = "Object URL: ${details.objectURL}", fontSize = 22.sp)
                Text(text = "Object Wikidata URL: ${details.objectWikidataURL}", fontSize = 22.sp)
            }
        }
    }
}