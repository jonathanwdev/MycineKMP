package domain.repository

import data.remote.movieDb.MovieDbApiService
import data.repository.MovieDbApi
import domain.models.DetailedMovie
import domain.models.Genre
import domain.models.Movie
import util.NetworkError
import util.Result

class Repository(
    private val api: MovieDbApiService
): MovieDbApi {
    override suspend fun getGenres(): Result<List<Genre>, NetworkError> {
        return api.getGenres()
    }

    override suspend fun getPopularMovies(pageNumber: Int): Result<List<Movie>, NetworkError> {
        return api.getPopularMovies(pageNumber)
    }

    override suspend fun getMoviesByGenre(
        genreId: Int,
        pageNumber: Int
    ): Result<List<Movie>, NetworkError> {
        return api.getMoviesByGenre(pageNumber, genreId)
    }

    override suspend fun getDetailedMovie(
        movieId: String,
    ): Result<DetailedMovie, NetworkError> {
        return api.getDetailedMovie(movieId)
    }


}