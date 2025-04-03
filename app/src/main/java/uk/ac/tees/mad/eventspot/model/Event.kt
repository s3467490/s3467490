package uk.ac.tees.mad.eventspot.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_table")
data class Event(
    @PrimaryKey val id:String = "",
    val title: String="",
    val date: String="",
    val time: String="",
    val location: String="",
    val latitude: Double=0.0,
    val longitude: Double=0.0,
    val details: String=""
)

