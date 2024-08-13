package domain.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailedMovie(
    @SerialName("adult")
    val adult: Boolean = false,
    @SerialName("backdrop_path")
    val backdropPath: String = "",
    @SerialName("budget")
    val budget: Int = 0,
    @SerialName("genres")
    val genres: List<GenreX> = listOf(),
    @SerialName("homepage")
    val homepage: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("imdb_id")
    val imdbId: String = "",
    @SerialName("origin_country")
    val originCountry: List<String> = listOf(),
    @SerialName("original_language")
    val originalLanguage: String = "",
    @SerialName("original_title")
    val originalTitle: String = "",
    @SerialName("overview")
    val overview: String = "",
    @SerialName("popularity")
    val popularity: Double = 0.0,
    @SerialName("poster_path")
    val posterPath: String = "",
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompany> = listOf(),
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountry> = listOf(),
    @SerialName("release_date")
    val releaseDate: String = "",
    @SerialName("revenue")
    val revenue: Int = 0,
    @SerialName("runtime")
    val runtime: Int = 0,
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage> = listOf(),
    @SerialName("status")
    val status: String = "",
    @SerialName("tagline")
    val tagline: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("video")
    val video: Boolean = false,
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
    @SerialName("vote_count")
    val voteCount: Int = 0,
    val imageUrl: String = "",
    val movieTime: String? = null
)