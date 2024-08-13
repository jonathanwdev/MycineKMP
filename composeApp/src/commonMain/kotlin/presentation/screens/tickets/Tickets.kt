package presentation.screens.tickets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import data.local.database.MyCinemaDatabase
import data.local.database.ReservationEntity
import kotlinx.coroutines.launch
import presentation.components.BottomNavBar
import presentation.components.ProfileHeader
import presentation.screens.tickets.components.TicketItem
import presentation.theme.Black

@Composable
fun TicketScreen(
    modifier: Modifier = Modifier,
    database: MyCinemaDatabase,
    navHost: NavHostController,
) {
    val scope = rememberCoroutineScope()
    val myReservations = remember {
        mutableStateListOf<ReservationEntity>()
    }

    suspend fun onUpdateScreen() {
        val reservations = database.getDao().findAll()
        myReservations.clear()
        myReservations.addAll(reservations)
    }

    suspend fun onDeleteReservation(reservation: ReservationEntity) {
        database.getDao().delete(reservation)
        onUpdateScreen()
    }

    LaunchedEffect(true) {
        scope.launch {
            onUpdateScreen()
        }
    }
    Scaffold(
        modifier = Modifier.background(Black).padding(15.dp),
        topBar = {
            ProfileHeader()
        },
        bottomBar = {
            BottomNavBar(
                navHost
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues).fillMaxSize().background(Black),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            item {
                Spacer(Modifier.height(10.dp))
            }
            items(myReservations.size) { item ->
                val reservation = myReservations[item]
                TicketItem(
                    modifier = Modifier,
                    reservation = reservation,
                    onDeleteClick = { reservation ->
                        scope.launch {
                            onDeleteReservation(reservation)
                        }
                    }
                )
            }
        }
    }
}