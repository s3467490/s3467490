package uk.ac.tees.mad.eventspot.ui.screen.home

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.eventspot.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val colorList = listOf(Color.Red, Color.Magenta, Color.Green, Color.Yellow, Color.Cyan)
    val eventList by viewModel.eventList.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    val context = LocalContext.current

    val refreshState = rememberPullToRefreshState()

    Scaffold(
        containerColor = Color.Black,
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 30.dp, start = 12.dp, bottom = 12.dp, end = 18.dp)) {
                Text("EventSpot",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                    )
                IconButton(onClick = {},
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
                Toast.makeText(context, "Refreshing..", Toast.LENGTH_SHORT).show()
            },
            modifier =  Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ){
            LazyColumn {
                items(eventList){ event->
                    EventItem(event, colorList.random())
                }
            }
        }
    }
}