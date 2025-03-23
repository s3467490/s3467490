package uk.ac.tees.mad.eventspot.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import uk.ac.tees.mad.eventspot.R
import uk.ac.tees.mad.eventspot.utils.Constants

@Composable
fun SplashScreen(navController: NavController) {
    val appName = "EventSpot"
    var displayedText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        appName.forEachIndexed { index, _ ->
            delay(100L)
            displayedText = appName.substring(0, index + 1)
        }
        if(displayedText.length>=9){
            navController.navigate(Constants.HOME_SCREEN){
                popUpTo(Constants.SPLASH_SCREEN){
                    inclusive = true
                }
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.event),
                contentDescription = "app_icon",
                modifier = Modifier
                    .padding(8.dp)
                    .size(78.dp)
            )
            Text(
                text = displayedText,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}