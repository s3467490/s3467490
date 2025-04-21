package uk.ac.tees.mad.eventspot.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson

@Entity(tableName = "event_table")
data class Event(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val title: String="",
    val date: String="",
    val time: String="",
    val location: String="",
    val latitude: Double=0.0,
    val longitude: Double=0.0,
    val details: String=""
){
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): Event {
            return Gson().fromJson(json, Event::class.java)
        }
    }
}

