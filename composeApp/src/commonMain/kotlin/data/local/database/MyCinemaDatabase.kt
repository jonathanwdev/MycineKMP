package data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ReservationEntity::class], version = 1)
abstract class MyCinemaDatabase: RoomDatabase(), DB {
    abstract fun getDao(): ReservationsDao

    override fun clearAllTables() {
        super.clearAllTables()
    }
}

internal const val dbFileName = "my_cinema.db"

interface DB {
    fun clearAllTables(){}
}