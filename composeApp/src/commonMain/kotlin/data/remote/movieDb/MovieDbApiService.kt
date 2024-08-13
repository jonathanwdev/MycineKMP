package data.remote.movieDb

import domain.models.DetailedMovie
import domain.models.Genre
import domain.models.GetGenresResponse
import domain.models.GetMoviesResponse
import domain.models.Movie
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.SerializationException
import util.NetworkError
import util.Result
import util.convertMinutesToMovieTime
import java.nio.channels.UnresolvedAddressException

class MovieDbApiService() : KtorApi() {
    companion object {
        const val GET_GENRES = "${MOVIE_DB_BASE_URL}/genre/movie/list?language=en"
        const val GET_MOVIES_BY_GENRE =
            "${MOVIE_DB_BASE_URL}/discover/movie?include_adult=false&include_video=false&language=en-US&page={PAGE_NUMBER}&sort_by=popularity.desc&with_genres={GENRE_ID}"
        const val GET_POPULAR_MOVIES =
            "${MOVIE_DB_BASE_URL}/movie/popular?language=en-US&page={PAGE_NUMBER}"
        const val GET_DETAILED_MOVIE =
            "${MOVIE_DB_BASE_URL}/movie/{MOVIE_ID}?language=en-US"
    }

    suspend fun getGenres(): Result<List<Genre>, NetworkError> {
        val response = try {
            client.get(
                urlString = GET_GENRES
            )
        } catch (err: UnresolvedAddressException) {
            return Result.Error(NetworkError.SERIALIZATION)
        } catch (err: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when (response.status.value) {
            in 200..299 -> {
                val getGenresResponse = response.body<GetGenresResponse>()
                return Result.Success(getGenresResponse.genres)
            }

            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

    suspend fun getMoviesByGenre(pageNumber: Int, genreId: Int): Result<List<Movie>, NetworkError> {
        val response = try {
            val url = GET_MOVIES_BY_GENRE.replace("{GENRE_ID}", genreId.toString())
                .replace("{PAGE_NUMBER}", pageNumber.toString())
            client.get(
                urlString = url,
            )
        } catch (err: UnresolvedAddressException) {
            return Result.Error(NetworkError.SERIALIZATION)
        } catch (err: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when (response.status.value) {
            in 200..299 -> {
                val getMoviesByGenreResponse = response.body<GetMoviesResponse>()
                return Result.Success(
                    getMoviesByGenreResponse.results.map { it.copy(imageUrl = "${IMAGE_BASE_URL}${it.posterPath}") }
                )
            }

            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

    suspend fun getPopularMovies(pageNumber: Int): Result<List<Movie>, NetworkError> {
        val response = try {
            client.get(
                urlString = GET_POPULAR_MOVIES.replace("{PAGE_NUMBER}", pageNumber.toString()),
            )
        } catch (err: UnresolvedAddressException) {
            return Result.Error(NetworkError.SERIALIZATION)
        } catch (err: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when (response.status.value) {
            in 200..299 -> {
                val getPopularMoviesResponse = response.body<GetMoviesResponse>()
                return Result.Success(
                    getPopularMoviesResponse.results.map { it.copy(imageUrl = "${IMAGE_BASE_URL}${it.posterPath}") }
                )
            }

            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

    suspend fun getDetailedMovie(movieId: String): Result<DetailedMovie, NetworkError> {
        val response = try {
            client.get(
                urlString = GET_DETAILED_MOVIE.replace("{MOVIE_ID}", movieId),
            )
        } catch (err: UnresolvedAddressException) {
            return Result.Error(NetworkError.SERIALIZATION)
        } catch (err: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when (response.status.value) {
            in 200..299 -> {
                val getDetailedResponse = response.body<DetailedMovie>()
                return Result.Success(
                    getDetailedResponse.copy(
                        imageUrl = "${IMAGE_BASE_URL}${getDetailedResponse.posterPath}",
                        movieTime = convertMinutesToMovieTime(getDetailedResponse.runtime)
                    )
                )
            }

            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

}


