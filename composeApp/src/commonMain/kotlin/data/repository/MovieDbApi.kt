package data.repository

import domain.models.DetailedMovie
import domain.models.Genre
import domain.models.Movie
import util.NetworkError
import util.Result

interface MovieDbApi {
    suspend fun getGenres(): Result<List<Genre>, NetworkError>
    suspend fun getPopularMovies(pageNumber: Int): Result<List<Movie>, NetworkError>
    suspend fun getMoviesByGenre(genreId: Int, pageNumber: Int): Result<List<Movie>, NetworkError>
    suspend fun getDetailedMovie(movieId: String): Result<DetailedMovie, NetworkError>

}