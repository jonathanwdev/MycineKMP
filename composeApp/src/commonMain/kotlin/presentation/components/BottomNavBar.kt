package presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.filled.LocalMovies
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import presentation.navigation.MyCinemaScreens

import presentation.theme.Black
import presentation.theme.White
import presentation.theme.setAppGradient

@Composable
fun BottomNavBar(
    navHost: NavHostController,
) {
    Row(
        modifier = Modifier
            .background(Black)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .height(55.dp)
            .setAppGradient()
            .padding(horizontal = 25.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        IconButton(onClick = { navHost.navigate(MyCinemaScreens.Home.title) }) {
            Icon(
                imageVector = Icons.Default.Home, contentDescription = null,
                modifier = Modifier.size(35.dp),
                tint = if (navHost.currentDestination?.route == MyCinemaScreens.Home.title) White else Black
            )
        }
        IconButton(onClick = {  navHost.navigate(MyCinemaScreens.MyTickets.title) }) {
            Icon(
                imageVector = Icons.Default.LocalMovies, contentDescription = null,
                modifier = Modifier.size(35.dp),
                tint = if (navHost.currentDestination?.route == MyCinemaScreens.MyTickets.title) White else Black

            )
        }
        IconButton(onClick = {  navHost.navigate(MyCinemaScreens.MyProfile.title) }) {
            Icon(
                imageVector = Icons.Default.Person, contentDescription = null,
                modifier = Modifier.size(35.dp),
                tint = if (navHost.currentDestination?.route == MyCinemaScreens.MyProfile.title) White else Black
            )
        }
    }
}

