package presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import presentation.components.ImageLoader
import presentation.components.TextSizes
import presentation.components.Typography
import presentation.theme.Black
import presentation.theme.Pink

@Composable
fun ProfileHeader() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().background(Black)
        ) {
            Column {
                Typography(
                    text = "Hello John",
                    weight = FontWeight.SemiBold,
                    color = Pink,
                    size = TextSizes.LARGE
                )
                Spacer(modifier = Modifier.height(3.dp))
                Typography(
                    text = "Book your favorite movie !!",
                    weight = FontWeight.SemiBold,
                    size = TextSizes.MEDIUM
                )

            }
            ImageLoader(
                url = "https://midias.correiobraziliense.com.br/_midias/jpg/2024/02/23/675x450/1_naruto-35164382.jpg?20240531191417?20240531191417",
                modifier = Modifier
                    .size(65.dp)
                    .clip(CircleShape)
            )
        }
    }
}

