package presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import presentation.theme.White

enum class TextSizes{
    SMALL,
    MEDIUM,
    LARGE,
    LARGER,
    TITLE,
    HEADING,
}

@Composable
fun Typography(
    size: TextSizes = TextSizes.MEDIUM,
    text: String,
    color: Color = White,
    weight: FontWeight? = null
) {
    val fontSize = when(size) {
        TextSizes.SMALL -> 10
        TextSizes.MEDIUM -> 12
        TextSizes.LARGE -> 14
        TextSizes.TITLE -> 16
        TextSizes.LARGER -> 20
        TextSizes.HEADING -> 28
    }.sp

    Text(
        text = text,
        fontSize = fontSize,
        color = color,
        fontWeight = weight,
    )
}


