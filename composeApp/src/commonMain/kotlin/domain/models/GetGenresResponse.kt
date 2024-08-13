package domain.models

import kotlinx.serialization.Serializable



@Serializable
data class GetGenresResponse(
    val genres: List<Genre>
)