package uk.ac.tees.mad.eventspot.utils

import android.location.Location

object Utils {
    fun calculateDistance(location1:Location, lat2: Double, lon2: Double): Float {
        val location2 = Location("").apply {
            latitude = lat2
            longitude = lon2
        }
        return location1.distanceTo(location2) / 1000
    }
}