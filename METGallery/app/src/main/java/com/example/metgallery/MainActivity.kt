package com.example.metgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.metgallery.ui.elemDetails.DetailsScreen
import com.example.metgallery.ui.elemDetails.ElemDetailsRoute
import com.example.metgallery.ui.elements.ElementsRoute
import com.example.metgallery.ui.elements.SearchViewScreen
import com.example.metgallery.ui.favorite.FavoritesRoute
import com.example.metgallery.ui.favorite.FavoritesScreen
import com.example.metgallery.ui.theme.METGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            METGalleryTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ElementsRoute,
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                    modifier = Modifier,
                ) {
                    composable<ElementsRoute> {
                        SearchViewScreen(
                            onResultClick = { id ->
                                navController.navigate(ElemDetailsRoute(id))
                            },
                            onFavTextClick = {
                                navController.navigate(FavoritesRoute)
                            }
                        )
                    }
                    composable<ElemDetailsRoute> {
                        DetailsScreen(onUpClick = { navController.navigateUp() })
                    }
                    composable(FavoritesRoute) {
                        FavoritesScreen(
                            onFavoriteClick = { id ->
                                navController.navigate(ElemDetailsRoute(id))
                            },
                            onUpClick = { navController.navigateUp() }
                        )
                    }
                }
            }
        }
    }
}
