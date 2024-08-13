package presentation.screens.movie.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import presentation.components.TextSizes
import presentation.components.Typography
import presentation.theme.setAppGradient

@Composable
fun ReservationButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    label: String,
    onClick: () -> Unit
) {
    Button(
        enabled = enabled,
        onClick = {
            onClick.invoke()
        },
        modifier = modifier.fillMaxWidth().height(50.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(
            modifier = Modifier
                .setAppGradient().fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Typography(
                text = label,
                color = Color.White,
                weight = FontWeight.SemiBold,
                size = TextSizes.LARGER
            )
        }
    }
}