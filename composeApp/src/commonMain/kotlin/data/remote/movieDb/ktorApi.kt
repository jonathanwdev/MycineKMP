package data.remote.movieDb

import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


abstract class KtorApi() {

    companion object {
        const val API_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlOTQ4ZGMyODA5YTFkNjdlOGU3ZjE4MTE3NTVjNmFiNiIsIm5iZiI6MTcyMDY5MzQ0NS42MDgzMzYsInN1YiI6IjY0NjEzNmExZGJiYjQyMDE3MGE5MDg4NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.nUun1Ac16CMnAyDmJC-lGZSGxS_-Zbb0nfNY7MvQOnY"
        const val API_KEY = ""
        const val MOVIE_DB_BASE_URL = "https://api.themoviedb.org/3"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original"
    }

    val client = HttpClient {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(API_TOKEN, API_KEY)
                }
            }
        }
    }
}