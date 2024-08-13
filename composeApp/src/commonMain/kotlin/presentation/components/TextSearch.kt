package presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import presentation.theme.Gray40

import presentation.theme.Pink
import presentation.theme.Purple40
import presentation.theme.White

@Composable
fun TextSearch(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String? = "Search",
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(White)
    ) {
        TextField(
            modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = White,
                focusedTextColor = Purple40,
                unfocusedTextColor = Purple40,
                unfocusedContainerColor = White,
                disabledContainerColor = Gray40,
                focusedIndicatorColor = White,
                unfocusedIndicatorColor = White,
                focusedLeadingIconColor = Pink,
                disabledLeadingIconColor = Purple40,
                unfocusedLeadingIconColor = Purple40
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                )
            },
            placeholder = {
                Text(text = placeholder!!)
            }
        )
    }
}
