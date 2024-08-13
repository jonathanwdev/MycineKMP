package presentation.screens.movie

import PlatformName
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import getPlatform
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import data.local.database.MyCinemaDatabase
import data.local.database.ReservationEntity
import domain.models.DetailedMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import presentation.components.ImageLoader
import presentation.navigation.MyCinemaScreens
import presentation.screens.movie.components.DayItem
import presentation.screens.movie.components.FakeHeader
import presentation.screens.movie.components.MovieHeader
import presentation.theme.Black
import presentation.screens.movie.components.MovieDetails

data class MovieScreenData(
    val dayList: List<DayItem>,
    val currentSelected: Int,
    val today: Int,
    val onDaySelect: (day: Int) -> Unit,
    val onReserveClick: suspend (detailedMovie: DetailedMovie) -> Unit,
    val onGoBack: () -> Unit,
    val movie: DetailedMovie?,
    val coroutineScope: CoroutineScope
)

@Composable
fun DesktopView(modifier: Modifier = Modifier, state: MovieScreenData) {
    Row(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Black),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(600.dp)
                .background(Color.Black),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Box() {
                ImageLoader(
                    modifier = Modifier,
                    url = if (state.movie != null) state.movie.imageUrl else ""
                )
                FakeHeader {
                    state.onGoBack()
                }
            }

        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            MovieHeader(
                movieCategories = if (state.movie != null) state.movie.genres.map { it.name } else emptyList(),
                movieLength = if (state.movie != null) state.movie.movieTime else "00hrs 00mins",
                imdb = if (state.movie != null) "${state.movie.voteAverage}/10" else "0/10",
                movieTitle = if (state.movie != null) state.movie.title else "-- -- --",
            )
            MovieDetails(
                modifier = Modifier,
                synopsisLines = 15,
                isSelected = state.currentSelected,
                dayList = state.dayList,
                today = state.today,
                onDaySelect = state.onDaySelect,
                synopsis = if(state.movie != null) state.movie.overview else "-- -- --",
                onReserveClick = {
                    state.coroutineScope.launch {
                        state.onReserveClick(state.movie!!)

                    }
                }
            )
        }
    }
}

@Composable
fun MobileView(modifier: Modifier = Modifier, state: MovieScreenData) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Black),

        ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)

        ) {
            ImageLoader(
                modifier = Modifier.fillMaxWidth(),
                url = if (state.movie != null) state.movie.imageUrl else ""
            )
            FakeHeader {
                state.onGoBack()
            }
            MovieHeader(
                modifier = Modifier.align(Alignment.BottomStart),
                movieCategories = if (state.movie != null) state.movie.genres.map { it.name } else emptyList(),
                movieLength = if (state.movie != null) state.movie.movieTime else "00hrs 00mins",
                imdb = if (state.movie != null) "${state.movie.voteAverage}/10" else "0/10",
                movieTitle = if (state.movie != null) state.movie.title else "-- -- --",
            )
        }
        MovieDetails(
            modifier = Modifier.fillMaxWidth(),
            synopsisLines = 4,
            isSelected = state.currentSelected,
            dayList = state.dayList,
            today = state.today,
            onDaySelect = state.onDaySelect,
            synopsis = if(state.movie != null) state.movie.overview else "-- -- --",
            onReserveClick = {
                state.coroutineScope.launch {
                    state.onReserveClick(state.movie!!)
                }
            }
        )
    }
}


@Composable
fun MovieScreen(
    modifier: Modifier = Modifier,
    navHost: NavHostController,
    database: MyCinemaDatabase,
    movieId: String,
    viewModel: MovieViewModel = koinInject()
) {
    val coroutineScope = rememberCoroutineScope()
    val platform = getPlatform().name

    val uiState by viewModel.uiState.collectAsState()

    suspend fun onReserveClick(detailedMovie: DetailedMovie) {
        database.getDao().insert(ReservationEntity(
            movieId = detailedMovie.id,
            title = detailedMovie.title,
            date = viewModel.createDate().toString(),
            posterUrl = detailedMovie.imageUrl,
        ))
        navHost.navigate(MyCinemaScreens.MyTickets.title)
    }

    val state = MovieScreenData(
        dayList = uiState.dayItemLists,
        currentSelected = uiState.isSelected,
        today = uiState.today,
        onDaySelect = viewModel::onSelectDay,
        movie = uiState.movie,
        onGoBack = { navHost.popBackStack() },
        onReserveClick = ::onReserveClick,
        coroutineScope = coroutineScope
    )




    LaunchedEffect(true) {
        coroutineScope.launch {
            viewModel.fetchMovie(movieId)
            val data = database.getDao().findAll()
        }
    }

    Scaffold(
        modifier = Modifier.background(Black),
    ) { paddingValues ->
        if(uiState.isLoadingMovie) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(modifier = Modifier.size(85.dp))
            }
        }
        if(uiState.fetchMovieError == null) {
            when (platform) {
                PlatformName.DESKTOP -> DesktopView(
                    modifier = modifier.padding(paddingValues),
                    state = state
                )
                PlatformName.ANDROID -> MobileView(
                    modifier = modifier.padding(paddingValues),
                    state = state
                )
            }
        }
    }
}