package uk.ac.tees.mad.eventspot.ui.screen.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.LatLng
import uk.ac.tees.mad.eventspot.R
import uk.ac.tees.mad.eventspot.model.Event

@Composable
fun DetailsScreen(event: Event, viewModel: DetailsViewModel = hiltViewModel()) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            Text("Event Details",
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 30.dp, start = 16.dp)
                )
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()){
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                Text(event.title,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text("Place: ${event.location}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 12.dp)
                    )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text("Date: ${event.date}", color = Color.Gray)
                    Spacer(Modifier.weight(1f))
                    Text("Time: ${event.time}", color = Color.Gray)
                }
                Column(modifier = Modifier.padding(vertical = 12.dp)) {
                    Text("Details: ", fontSize = 18.sp,fontWeight = FontWeight.Bold)
                    Text(event.details)
                }
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    ){
                    GoogleMapScreen(LatLng(event.latitude, event.longitude), event.location, event.title)
                    ElevatedButton(onClick = {
                        viewModel.openGoogleMaps(context, event.latitude, event.longitude)
                    }, modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomStart)) {
                        Text("Get Direction")
                    }
                }
                OutlinedButton(onClick = {viewModel.addToFavorite(event, context)},
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .align(Alignment.CenterHorizontally)) {
                    Row {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.baseline_favorite_border_24),
                            contentDescription = "favorite",
                            modifier = Modifier.padding(end = 2.dp)
                        )
                        Text("Add to Favorite")
                    }
                }
            }
        }
    }
}