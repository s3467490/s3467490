package uk.ac.tees.mad.eventspot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import uk.ac.tees.mad.eventspot.navigation.AppNavigation
import uk.ac.tees.mad.eventspot.ui.theme.EventSpotTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventSpotTheme {
                AppNavigation()
            }
        }
    }
}