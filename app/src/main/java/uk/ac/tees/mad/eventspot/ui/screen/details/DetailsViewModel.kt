package uk.ac.tees.mad.eventspot.ui.screen.details

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uk.ac.tees.mad.eventspot.data.Repository
import uk.ac.tees.mad.eventspot.model.Event
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: Repository
) :ViewModel() {

    fun addToFavorite(event: Event, context: Context){
        viewModelScope.launch {
            repository.addEvent(event)
            Toast.makeText(context, "Added to Favorite", Toast.LENGTH_SHORT).show()
        }
    }
}