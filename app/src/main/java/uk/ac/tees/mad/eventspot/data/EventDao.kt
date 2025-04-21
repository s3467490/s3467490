package uk.ac.tees.mad.eventspot.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.eventspot.model.Event

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEvent(entity:Event)

    @Query("SELECT * FROM event_table")
    fun getEvents():Flow<List<Event>>

    @Delete
    suspend fun deleteEvent(entity: Event)
}