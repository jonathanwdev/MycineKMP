package presentation.navigation

import kotlinx.serialization.Serializable



enum class MyCinemaScreens(val title: String) {
    Home(title = "home"),
    Movie(title = "movie/{movieId}"),
    MyTickets(title = "my_tickets"),
    MyProfile(title = "my_profile")
}

