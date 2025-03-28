package uk.ac.tees.mad.eventspot.model

data class Event(
    val title: String="",
    val date: String="",
    val time: String="",
    val location: String="",
    val latitude: Double=0.0,
    val longitude: Double=0.0,
    val details: String=""
)

