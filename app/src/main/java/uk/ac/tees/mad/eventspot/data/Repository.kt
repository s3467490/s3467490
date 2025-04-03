package uk.ac.tees.mad.eventspot.data

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.eventspot.model.Event

interface Repository {
    suspend fun addEvent(entity: Event)
    fun getEvents(): Flow<List<Event>>
    suspend fun deleteAllEvents()
}