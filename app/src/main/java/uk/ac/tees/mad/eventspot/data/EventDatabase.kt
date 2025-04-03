package uk.ac.tees.mad.eventspot.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uk.ac.tees.mad.eventspot.model.Event

@Database(entities = [Event::class], version = 1, exportSchema = false)
abstract class EventDatabase:RoomDatabase() {
    abstract fun eventDao():EventDao
    companion object{
        @Volatile
        private var INSTANCE:EventDatabase? = null

        fun getDatabase(context: Context):EventDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EventDatabase::class.java,
                    "event_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}