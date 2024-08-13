package presentation.screens.tickets.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import getPlatform
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import data.local.database.ReservationEntity
import presentation.components.ImageLoader
import presentation.components.TextSizes
import presentation.components.Typography
import presentation.theme.Pink
import presentation.theme.Pink40
import presentation.theme.Pink80
import presentation.theme.Purple40
import presentation.theme.White
import presentation.theme.setAppGradient

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TicketItem(
    modifier: Modifier = Modifier,
    reservation: ReservationEntity,
    onDeleteClick: (reservation: ReservationEntity) -> Unit
) {
    val windowSize = when(getPlatform().name) {
        PlatformName.ANDROID -> 300
        else -> 600
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Purple40)
            .fillMaxWidth().padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.width(windowSize.dp)
        ) {
            ImageLoader(
                url = reservation.posterUrl, modifier = Modifier
                    .size(65.dp)
                    .clip(CircleShape)
            )
            Column {
                Text(
                    text = reservation.title,
                    color = White,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Typography(text = reservation.date)
            }
        }

        IconButton(
            modifier = Modifier.width(40.dp),
            onClick = {
                onDeleteClick.invoke(reservation)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                tint = White,
                modifier = Modifier.size(24.dp),
                contentDescription = null
            )
        }
    }
}