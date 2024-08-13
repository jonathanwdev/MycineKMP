package presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import domain.models.Movie
import kotlinx.coroutines.launch
import presentation.components.BottomNavBar
import presentation.components.ImageLoader
import presentation.components.TextSearch
import presentation.components.TextSizes
import presentation.components.Typography
import presentation.screens.home.components.CategoryCard
import presentation.components.ProfileHeader
import presentation.theme.Black
import getPlatform
import org.koin.compose.koinInject
import presentation.navigation.MyCinemaScreens
import presentation.screens.home.components.Category


data class AdaptLayout(
    val cells: GridCells,
    val vertArrangement: Arrangement.HorizontalOrVertical,
    val horArrangement: Arrangement.HorizontalOrVertical,
    val posterModifier: Modifier
)


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHost: NavHostController,
    viewModel: HomeViewModel = koinInject()
) {
    val platform = getPlatform().name
    val scrollCategoryState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val uiState by viewModel.uiState.collectAsState()

    fun onMovieClick(movie: Movie) {
        navHost.navigate(MyCinemaScreens.Movie.title.replace("{movieId}", movie.id.toString()))
    }


    val currentGridLayout = when (platform) {
        PlatformName.ANDROID -> AdaptLayout(
            cells = GridCells.Adaptive(160.dp),
            vertArrangement = Arrangement.spacedBy(15.dp),
            horArrangement = Arrangement.spacedBy(12.dp),
            posterModifier = Modifier
        )

        PlatformName.DESKTOP -> AdaptLayout(
            cells = GridCells.Adaptive(250.dp),
            vertArrangement = Arrangement.spacedBy(15.dp),
            horArrangement = Arrangement.spacedBy(12.dp),
            posterModifier = Modifier.height(360.dp)
        )
    }

    Scaffold(
        modifier = Modifier.background(Black).padding(15.dp),
        topBar = {
            ProfileHeader()
        },
        bottomBar = {
            BottomNavBar(
                navHost = navHost
            )
        }
    ) { paddingValues ->
        Column(modifier = modifier.padding(paddingValues).fillMaxSize().background(Black)) {
            Spacer(modifier = Modifier.height(20.dp))
            TextSearch(value = uiState.textSearch, onValueChange = { viewModel.onTextChange(it) })
            Spacer(modifier = Modifier.height(30.dp))
            Typography(
                text = "Movie Categories",
                size = TextSizes.TITLE,
                weight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(15.dp))
            LazyRow(
                state = scrollCategoryState,
                modifier = Modifier.fillMaxWidth().draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        coroutineScope.launch {
                            scrollCategoryState.scrollBy(-delta)
                        }
                    },
                )
            ) {

                if (uiState.isLoadingGenres) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                } else {
                    items(uiState.genres.size) {
                        val item = uiState.genres[it]
                        CategoryCard(
                            isSelected = item.id == uiState.selectedGenre,
                            category = Category(id = item.id, name = item.name),
                            onCategoryClick = {
                                viewModel.onSelectGenre(item.id)
                                viewModel.fetchMoviesByGenre(item.id, 1)
                            }

                        )
                        Spacer(modifier = Modifier.width(10.dp))


                    }
                }

            }
            Spacer(modifier = Modifier.height(15.dp))
            LazyVerticalGrid(
                columns = currentGridLayout.cells,
                verticalArrangement = currentGridLayout.vertArrangement,
                horizontalArrangement = currentGridLayout.horArrangement,
                contentPadding = PaddingValues(top = 15.dp, bottom = 10.dp),
            ) {
                if (uiState.isLoadingMovies) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                } else {
                    if(uiState.textSearch.isNotEmpty()){
                        items(
                            count = uiState.moviesFound.size,
                            span = {
                                GridItemSpan(1)
                            }
                        ) { item ->
                            ImageLoader(
                                modifier = currentGridLayout
                                    .posterModifier
                                    .clickable {
                                        onMovieClick(uiState.moviesFound[item])
                                    },
                                url = uiState.moviesFound[item].imageUrl!!
                            )
                        }
                    }else {
                        items(
                            count = uiState.movies.size,
                            span = {
                                GridItemSpan(1)
                            }
                        ) { item ->
                            ImageLoader(
                                modifier = currentGridLayout
                                    .posterModifier
                                    .clickable {
                                        onMovieClick(uiState.movies[item])
                                    },
                                url = uiState.movies[item].imageUrl!!
                            )
                        }

                    }
                }

            }

        }
    }


}



