package uk.ac.tees.mad.eventspot.ui.screen.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun GoogleMapScreen(location:LatLng, title:String, snippet:String) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 16f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize()
            .clip(RoundedCornerShape(16.dp)),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = rememberMarkerState(position = location),
            title = title,
            snippet = snippet
        )
    }
}