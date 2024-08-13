package domain.models

import presentation.screens.movie.components.DayItem
import util.NetworkError


data class HomeUiState(
    val movies: List<Movie> = emptyList(),
    val isLoadingMovies: Boolean = false,
    val fetchMoviesError: NetworkError? = null,
    val genres: List<Genre> = emptyList(),
    val isLoadingGenres: Boolean = false,
    val fetchGenresError: NetworkError? = null,
    val selectedGenre: Int = -1,
    val textSearch: String = "",
    val moviesFound: List<Movie> = emptyList(),
)

data class MovieUiState(
    val movie: DetailedMovie? = null,
    val isLoadingMovie: Boolean = false,
    val fetchMovieError: NetworkError? = null,
    val dayItemLists: List<DayItem> = emptyList(),
    val isSelected: Int = -1,
    val today: Int = -1
)