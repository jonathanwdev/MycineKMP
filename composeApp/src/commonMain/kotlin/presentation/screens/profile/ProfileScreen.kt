package presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import presentation.components.BottomNavBar
import presentation.components.ImageLoader
import presentation.components.ProfileHeader
import presentation.components.TextSizes
import presentation.components.Typography
import presentation.theme.Black
import presentation.theme.Pink

@Composable
fun ProfileScreen(modifier: Modifier = Modifier, navHost: NavHostController) {

    Scaffold(
        modifier = Modifier.background(Black).padding(15.dp),
        bottomBar = {
            BottomNavBar(
                navHost
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize().background(Black),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageLoader(
                url = "https://midias.correiobraziliense.com.br/_midias/jpg/2024/02/23/675x450/1_naruto-35164382.jpg?20240531191417?20240531191417",
                modifier = Modifier
                    .size(105.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Typography(
                text = "John Doe",
                size = TextSizes.LARGER,
                color = Pink,
                weight = FontWeight.Bold
            )
        }
    }
}