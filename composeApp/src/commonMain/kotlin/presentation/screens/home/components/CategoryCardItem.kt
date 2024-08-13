package presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import presentation.components.TextSizes
import presentation.components.Typography
import presentation.theme.Black
import presentation.theme.Purple40
import presentation.theme.White
import presentation.theme.setAppGradient

data class Category(
    val id: Int,
    val name: String,
)

@Composable
fun CategoryCard(isSelected: Boolean, category: Category, onCategoryClick: (id: Int) -> Unit) {
    Box(
        modifier = Modifier.height(60.dp)
            .clip(RoundedCornerShape(18.dp))
            .clickable {
                onCategoryClick(category.id)
            }
            .background(if(isSelected) White else Purple40)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 15.dp).fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Typography(
                text = category.name,
                color = if (!isSelected) White else Black,
                size = TextSizes.LARGE,
                weight = FontWeight.Bold
            )
        }
    }
}
