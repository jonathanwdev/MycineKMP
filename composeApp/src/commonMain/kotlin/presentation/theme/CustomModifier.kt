package presentation.theme

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush

val colorStops = arrayOf(
    0.6f to Purple40,
    1f to Pink,
)

fun Modifier.setAppGradient(isSelected: Boolean = false): Modifier {
    return when(isSelected){
        true -> this.background(White)
        else -> this.background(Brush.horizontalGradient(colorStops = colorStops))
    }
}