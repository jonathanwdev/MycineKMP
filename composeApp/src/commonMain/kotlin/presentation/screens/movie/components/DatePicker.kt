package presentation.screens.movie.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import presentation.components.TextSizes
import presentation.components.Typography
import presentation.theme.Black
import presentation.theme.Gray40
import presentation.theme.Gray80
import presentation.theme.Pink40
import presentation.theme.Purple40


data class DayItem(
    val numberDay: Int,
    val weekDay: String
)

@Composable
fun DatePicker(dayList: List<DayItem>, today: Int, isSelected: Int, onSelect: (day: Int) -> Unit) {
    val listState = rememberLazyListState(today - 1)
    val coroutineScope = rememberCoroutineScope()

    val enabledGradient = Brush.verticalGradient(
        colorStops = arrayOf(
            0.2f to Pink40,
            1f to Purple40,
        )
    )
    val disabledGradient = Brush.verticalGradient(
        colorStops = arrayOf(
            0.2f to Gray40,
            1f to Gray80,
        )
    )


    LazyRow(
        state = listState,
        modifier = Modifier.height(125.dp)
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta ->
                    coroutineScope.launch {
                        listState.scrollBy(-delta)
                    }
                },
            ),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(dayList.size) { item ->
            val day = dayList[item]
            val enabled = day.numberDay >= today
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .width(90.dp)
                    .clickable {
                        if (enabled) onSelect.invoke(day.numberDay)
                    }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            if (enabled) enabledGradient else disabledGradient
                        )
                ) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Icon(
                        imageVector = Icons.Default.Circle,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp),
                        tint = if (day.numberDay == isSelected) Color.Green.copy(alpha = .8f) else Black
                    )
                    Spacer(modifier = Modifier.height(7.dp))
                    Typography(text = day.weekDay, size = TextSizes.LARGE)
                    Spacer(modifier = Modifier.height(2.dp))
                    Typography(
                        text = day.numberDay.toString(),
                        size = TextSizes.HEADING,
                        weight = FontWeight.Bold
                    )
                }
            }

        }

    }
}
