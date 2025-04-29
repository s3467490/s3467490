package uk.ac.tees.mad.eventspot.ui.screen.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import uk.ac.tees.mad.eventspot.ui.screen.home.EventItem
import uk.ac.tees.mad.eventspot.utils.Constants
import java.net.URLEncoder

@Composable
fun FavoriteScreen(navController: NavController, viewModel: FavoriteViewModel = hiltViewModel()) {
    val events by viewModel.eventList.collectAsState()
    val colorList = listOf(Color.Red, Color.Magenta, Color.Green, Color.Yellow, Color.Cyan)
    val context = LocalContext.current
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            Text("My Favorites",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 30.dp, start = 16.dp)
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)){
            LazyColumn {
                itemsIndexed(events, key = {_, event-> event.id}){index, event ->
                    val dismissState = rememberSwipeToDismissBoxState(
                        confirmValueChange = {
                            if (it == SwipeToDismissBoxValue.EndToStart) {
                                viewModel.deleteEvent(context,event)
                                true
                            } else {
                                false
                            }
                        }
                    )
                    SwipeToDismissBox(
                        state = dismissState,
                        backgroundContent = {
                        }
                    ) {
                        EventItem(event,colorList[index%5],
                            onClick = {
                                val json = URLEncoder.encode(event.toJson(), "UTF-8")
                                navController.navigate(Constants.DETAILS_SCREEN+"/$json")
                            })
                    }
                }
            }
        }
    }
}