package uk.ac.tees.mad.eventspot.ui.screen.favorite

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.eventspot.data.Repository
import uk.ac.tees.mad.eventspot.model.Event
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: Repository
):ViewModel(){

    private val _eventList = MutableStateFlow<List<Event>>(emptyList())
    val eventList: StateFlow<List<Event>> get() = _eventList

    init {
        viewModelScope.launch {
            repository.getEvents().collect{
                _eventList.value = it
            }
        }
    }

    fun deleteEvent(context:Context, event: Event){
        viewModelScope.launch {
            repository.deleteEvent(event)
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
        }
    }
}