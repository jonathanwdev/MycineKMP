package presentation.screens.movie

import androidx.lifecycle.ViewModel
import domain.models.DetailedMovie
import domain.models.MovieUiState
import domain.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import util.Result
import util.createListOfDays
import java.time.LocalDate
import java.util.Calendar

class MovieViewModel(
    private val repository: Repository
) : ViewModel() {
    private val _uiState = MutableStateFlow(MovieUiState())
    val uiState: StateFlow<MovieUiState> = _uiState.asStateFlow()

    private val calendar = Calendar.getInstance()

    fun createDate(): LocalDate {
        return LocalDate.of(
            LocalDate.now().year,
            LocalDate.now().month,
            uiState.value.isSelected
        )
    }


    init {
        createListOfDays(calendar) { list ->
            _uiState.update { it.copy(dayItemLists = list) }
        }
        updateInitialState()
    }

    private fun updateInitialState() {
        val today = calendar.get(Calendar.DAY_OF_MONTH)
        _uiState.update { it.copy(today = today) }
        onSelectDay(today)

    }


    fun onSelectDay(day: Int) {
        _uiState.update { it.copy(isSelected = day) }
    }

    suspend fun onSchedule(day: Int, movie: DetailedMovie) {

    }

    suspend fun fetchMovie(movieId: String) {
        _uiState.update { it.copy(isLoadingMovie = true) }
        when (val movie = repository.getDetailedMovie(movieId)) {
            is Result.Success -> {
                _uiState.update { it.copy(isLoadingMovie = false, movie = movie.data) }
            }

            is Result.Error -> {
                _uiState.update { it.copy(isLoadingMovie = false, fetchMovieError = movie.error) }
            }
        }
    }

}