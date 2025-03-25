package uk.ac.tees.mad.eventspot.ui.screen.home

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import uk.ac.tees.mad.eventspot.utils.Constants
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor():ViewModel() {

    val db = FirebaseFirestore.getInstance()
    init {
        val eventList = Constants.events
        eventList.forEach {
            db.collection("events")
                .add(it)
        }
    }
}