package data.local.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import java.io.File

actual class DbConfigure {
    actual fun createDatabase(): MyCinemaDatabase {
        val dbFile = File(System.getProperty("java.io.tmpdir"), dbFileName)
        return Room
            .databaseBuilder<MyCinemaDatabase>(dbFile.absolutePath)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}