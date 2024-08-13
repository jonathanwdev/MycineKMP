package domain.models

import kotlinx.serialization.Serializable

@Serializable
data class GetMoviesResponse(
    val page: Int,
    val results: List<Movie>,
)
