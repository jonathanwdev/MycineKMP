import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import data.local.database.DbConfigure

import java.awt.Dimension

fun main() = application {
    val database = DbConfigure().createDatabase()
    Window(
        onCloseRequest = ::exitApplication,
        title = "MyCinemaKMP",
        resizable = false
    ) {
        window.minimumSize = Dimension(1400, 820)
        App(database)

    }
}
