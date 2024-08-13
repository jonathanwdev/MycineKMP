package presentation.screens.home


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.models.HomeUiState
import domain.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import util.Result

class HomeViewModel(
    private val repository: Repository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchGenres()
        fetchPopularMovies()
    }


    fun onSelectGenre(genreId: Int) {
        _uiState.update { it.copy(selectedGenre = genreId) }
    }

    fun onTextChange(value: String) {
        _uiState.update { it.copy(textSearch = value) }
        _uiState.update { it ->
            it.copy(moviesFound = it.movies.filter {
                it.title.lowercase().contains(value.lowercase())
            })
        }
    }

    private fun fetchGenres() {
        _uiState.update { it.copy(isLoadingGenres = true) }
        viewModelScope.launch {
            when (val response = repository.getGenres()) {
                is Result.Success -> {
                    _uiState.update { it.copy(isLoadingGenres = false, genres = response.data) }
                }

                is Result.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoadingGenres = false,
                            fetchGenresError = response.error
                        )
                    }
                }
            }

        }
    }

    private fun fetchPopularMovies() {
        _uiState.update { it.copy(isLoadingMovies = true) }
        viewModelScope.launch {
            when (val response = repository.getPopularMovies(1)) {
                is Result.Success -> {
                    _uiState.update { it.copy(isLoadingMovies = false, movies = response.data) }
                }

                is Result.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoadingMovies = false,
                            fetchMoviesError = response.error
                        )
                    }
                }
            }

        }
    }

    fun fetchMoviesByGenre(genreId: Int, pageNumber: Int) {
        _uiState.update { it.copy(isLoadingMovies = true) }
        viewModelScope.launch {
            when (val response = repository.getMoviesByGenre(genreId, pageNumber)) {
                is Result.Success -> {
                    _uiState.update { it.copy(isLoadingMovies = false, movies = response.data) }
                }

                is Result.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoadingMovies = false,
                            fetchMoviesError = response.error
                        )
                    }
                }
            }

        }
    }
}