package presentation.screens.movie.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import presentation.components.TextSizes
import presentation.components.Typography
import presentation.theme.Gray40

@Composable
fun MovieDetails(
    modifier: Modifier = Modifier,
    synopsisLines: Int = 6,
    synopsis: String,
    isSelected: Int,
    today: Int,
    dayList: List<DayItem>,
    onDaySelect: (day: Int) -> Unit,
    onReserveClick: () -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 18.dp)
    ) {

        item {
            Typography(text = "Synopsis:", size = TextSizes.TITLE, weight = FontWeight.Bold)
        }
        item {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                overflow = TextOverflow.Ellipsis,
                maxLines = synopsisLines,
                color = Gray40,
                text = synopsis,
            )
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
            DatePicker(dayList = dayList, isSelected = isSelected, today =  today,onSelect = onDaySelect)
            Spacer(modifier = Modifier.height(20.dp))
            ReservationButton(
                modifier = Modifier.fillMaxWidth(),
                enabled = true,
                label = "Make Reservation",
                onClick = {
                    onReserveClick.invoke()
                }
            )
        }

    }
}