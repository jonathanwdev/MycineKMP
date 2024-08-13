package presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import data.local.database.MyCinemaDatabase
import org.koin.compose.koinInject
import presentation.screens.home.HomeScreen
import presentation.screens.home.HomeViewModel
import presentation.screens.movie.MovieScreen
import presentation.screens.profile.ProfileScreen
import presentation.screens.tickets.TicketScreen

@Composable
fun MyCinemaNavigation(navHostController: NavHostController, database: MyCinemaDatabase) {

    NavHost(navController = navHostController, startDestination = MyCinemaScreens.Home.title) {
        composable(route = MyCinemaScreens.Home.title) {
            HomeScreen(modifier = Modifier, navHostController)
        }
        composable(route = MyCinemaScreens.Movie.title) {
            val movieId = it.arguments?.getString("movieId")!!
            MovieScreen(modifier = Modifier, navHostController, database, movieId = movieId)
        }
        composable(route = MyCinemaScreens.MyTickets.title) {
            TicketScreen(modifier = Modifier, database, navHostController)
        }
        composable(route = MyCinemaScreens.MyProfile.title) {
            ProfileScreen(modifier = Modifier, navHostController)
        }

    }
}