package di

import data.remote.movieDb.MovieDbApiService
import domain.repository.Repository
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import presentation.screens.home.HomeViewModel
import presentation.screens.movie.MovieViewModel


val appModule = module {
    single {
        MovieDbApiService()
    }
    single {
        Repository(get())
    }

}

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::MovieViewModel)
}