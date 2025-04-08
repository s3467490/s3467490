package uk.ac.tees.mad.eventspot.data

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.eventspot.model.Event

interface Repository {
    suspend fun addEvents(entities:List<Event>)
    fun getEvents(): Flow<List<Event>>
    suspend fun deleteAllEvents()
}