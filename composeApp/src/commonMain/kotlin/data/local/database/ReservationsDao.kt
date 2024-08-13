package data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Dao
interface ReservationsDao{
    @Insert
    suspend fun insert(reservation: ReservationEntity)

    @Delete
    suspend fun delete(reservation: ReservationEntity)

    @Query("SELECT * FROM ReservationEntity")
    suspend fun findAllAsFlow(): List<ReservationEntity>

    @Query("SELECT * FROM ReservationEntity")
    suspend fun findAll(): List<ReservationEntity>

}

@Entity
data class ReservationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val movieId: Int,
    val title: String,
    val date: String,
    val posterUrl: String,
)