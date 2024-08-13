import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import data.local.database.MyCinemaDatabase
import di.appModule
import di.viewModelModule
import org.koin.compose.KoinApplication

import presentation.navigation.MyCinemaNavigation
import presentation.theme.CineAppTheme


@Composable
fun App(database: MyCinemaDatabase) {
    CineAppTheme {

        val navHostController = rememberNavController()
        KoinApplication(
            application = {
                modules(appModule, viewModelModule)
            }
        ) {
            MyCinemaNavigation(navHostController, database)
        }
    }
}

