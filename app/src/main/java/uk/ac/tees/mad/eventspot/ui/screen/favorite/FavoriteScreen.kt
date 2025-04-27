package uk.ac.tees.mad.eventspot.ui.screen.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.eventspot.model.Event
import uk.ac.tees.mad.eventspot.ui.screen.home.EventItem

@Composable
fun FavoriteScreen() {
    val events = emptyList<Event>()
    val colorList = listOf(Color.Red, Color.Magenta, Color.Green, Color.Yellow, Color.Cyan)
    Scaffold(
        topBar = {
            Text("My Favorites",
                fontSize = 24.sp,
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
                                // call function here
                                true
                            } else {
                                false
                            }
                        }
                    )
                    SwipeToDismissBox(
                        state = dismissState,
                        backgroundContent = {
                            Box(
                                contentAlignment = Alignment.CenterEnd,
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                Text("Delete", modifier = Modifier.padding(16.dp))
                            }
                        }
                    ) {
                        EventItem(event,colorList[index%5]) { }
                    }
                }
            }
        }
    }
}