package uk.ac.tees.mad.eventspot.data

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.eventspot.model.Event
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val eventDao: EventDao
):Repository {
    override suspend fun addEvent(entity: Event) {
        eventDao.addEvent(entity)
    }

    override fun getEvents(): Flow<List<Event>> {
        return eventDao.getEvents()
    }

    override suspend fun deleteEvent(entity: Event) {
        eventDao.deleteEvent(entity)
    }

}