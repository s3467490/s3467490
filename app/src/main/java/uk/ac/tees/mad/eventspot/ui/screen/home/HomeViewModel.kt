package uk.ac.tees.mad.eventspot.ui.screen.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.ac.tees.mad.eventspot.model.Event
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fusedLocationClient: FusedLocationProviderClient,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private val _eventList = MutableStateFlow<List<Event>>(emptyList())
    val eventList: StateFlow<List<Event>> get() = _eventList

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> get() = _isRefreshing

    private val _hasPermission = MutableStateFlow(false)
    val hasPermission: StateFlow<Boolean> get() = _hasPermission

    private val _userLocation = MutableStateFlow<Location?>(null)
    val userLocation: StateFlow<Location?> get() = _userLocation

    init {
        fetchEvents()
        fetchLocation()
    }

    fun fetchEvents() {
        _isRefreshing.value = true
        db.collection("events")
            .get()
            .addOnSuccessListener { documents ->
                _isRefreshing.value = false
                _eventList.value = documents.mapNotNull { doc ->
                    doc.toObject(Event::class.java)
                }

            }
            .addOnFailureListener {
                _isRefreshing.value = false
            }
    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            _userLocation.value = Location("").apply {
                latitude = 51.5072
                longitude = 0.1276
            }
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                _userLocation.value = location
            }
            .addOnFailureListener {
                _userLocation.value = Location("").apply {
                    latitude = 51.5072
                    longitude = 0.1276
                }
            }
    }

    fun hasPermission(value: Boolean) {
        _hasPermission.value = value
    }

}