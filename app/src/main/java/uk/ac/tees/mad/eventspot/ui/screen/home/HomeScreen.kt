package uk.ac.tees.mad.eventspot.ui.screen.home

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uk.ac.tees.mad.eventspot.R
import uk.ac.tees.mad.eventspot.utils.Constants
import uk.ac.tees.mad.eventspot.utils.Utils.calculateDistance
import java.net.URLEncoder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {
    val colorList = listOf(Color.Red, Color.Magenta, Color.Green, Color.Yellow, Color.Cyan)
    val eventList by viewModel.eventList.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val userLocation by viewModel.userLocation.collectAsState()
    var sliderValue by remember { mutableFloatStateOf(50f) }

    val refreshState = rememberPullToRefreshState()

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.hasPermission(true)
        }
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    Scaffold(
        containerColor = Color.Black,
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 30.dp, start = 12.dp, bottom = 12.dp, end = 18.dp)
            ) {
                Text(
                    "EventSpot",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .border(1.dp, color = Color.White, shape = CircleShape)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_favorite_border_24),
                        contentDescription = "Favorite",
                        tint = Color.White
                    )
                }
            }
        }
    ) { paddingValues ->
        PullToRefreshBox(
            state = refreshState,
            isRefreshing = isRefreshing,
            onRefresh = {
                viewModel.fetchEvents()
            },
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            LazyColumn {

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                            //.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Distance from you: ${sliderValue.toInt()} km",
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        Slider(
                            value = sliderValue,
                            onValueChange = { sliderValue = it },
                            valueRange = 50f..500f,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
                itemsIndexed(eventList.filter {
                    calculateDistance(userLocation!!, it.latitude, it.longitude) < sliderValue
                }) { index, event ->
                    EventItem(event, colorList[index%5],
                        onClick = {
                            val json = URLEncoder.encode(event.toJson(), "UTF-8")
                            navController.navigate(Constants.DETAILS_SCREEN+"/$json")
                        })
                }
            }
        }
    }
}