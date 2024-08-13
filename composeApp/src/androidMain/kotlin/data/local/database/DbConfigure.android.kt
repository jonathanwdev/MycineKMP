package data.local.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

actual class DbConfigure(private val context: Context) {
    actual fun createDatabase(): MyCinemaDatabase {
        val dbFile = context.getDatabasePath(dbFileName)
        return Room
            .databaseBuilder<MyCinemaDatabase>(
                context = context,
                name = dbFile.absolutePath
            )
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}