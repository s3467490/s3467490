package uk.ac.tees.mad.eventspot.ui.screen.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.ac.tees.mad.eventspot.model.Event
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor():ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private val _eventList = MutableStateFlow<List<Event>>(emptyList())
    val eventList:StateFlow<List<Event>> get() = _eventList
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing:StateFlow<Boolean> get() = _isRefreshing

    init {
        fetchEvents()
    }

    fun fetchEvents(){
        _isRefreshing.value = true
        db.collection("events")
            .get()
            .addOnSuccessListener { documents->
                _isRefreshing.value = false
                _eventList.value = documents.mapNotNull { doc->
                    doc.toObject(Event::class.java)
                }

            }
            .addOnFailureListener {
                _isRefreshing.value = false
            }
    }

}