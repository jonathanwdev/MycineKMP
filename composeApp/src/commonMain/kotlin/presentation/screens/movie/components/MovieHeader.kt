package presentation.screens.movie.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import presentation.components.TextSizes
import presentation.components.Typography
import presentation.theme.Black
import presentation.theme.Gray40

@Composable
private fun CategoryItem(modifier: Modifier = Modifier, category: String) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(7.dp))
            .background(Gray40.copy(alpha = .9f))
            .padding(vertical = 5.dp, horizontal = 10.dp)
    ) {
        Typography(text = category, size = TextSizes.SMALL)
    }
}

@Composable
fun MovieHeader(
    modifier: Modifier = Modifier,
    movieTitle: String,
    movieLength: String? = "-- / --",
    imdb: String? = "--",
    movieCategories: List<String>
) {
    val gradientList = Brush.verticalGradient(
        listOf(Color.Transparent, Black),
        startY = 20.0f,
        endY = 270.0f
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(gradientList)
    ) {
        Column(
            modifier = Modifier.padding(start = 18.dp, end = 18.dp)
        ) {
            Typography(text = movieTitle, size = TextSizes.HEADING, weight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(5.dp))
            Typography(text = "IMDb: $imdb", size = TextSizes.LARGE, weight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(5.dp))
            LazyRow {
                items(movieCategories.size) { item ->
                    CategoryItem(
                        modifier = Modifier.padding(end = 5.dp),
                        category = movieCategories[item]
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                Typography(
                    text = "Movie Length: ",
                    size = TextSizes.LARGE,
                    weight = FontWeight.Bold
                )
                Typography(text = movieLength!!, size = TextSizes.LARGE, color = Gray40)

            }
        }
    }
}

