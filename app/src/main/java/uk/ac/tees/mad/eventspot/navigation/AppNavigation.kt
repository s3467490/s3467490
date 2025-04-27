package uk.ac.tees.mad.eventspot.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import uk.ac.tees.mad.eventspot.model.Event
import uk.ac.tees.mad.eventspot.ui.screen.details.DetailsScreen
import uk.ac.tees.mad.eventspot.ui.screen.favorite.FavoriteScreen
import uk.ac.tees.mad.eventspot.ui.screen.home.HomeScreen
import uk.ac.tees.mad.eventspot.ui.screen.home.HomeViewModel
import uk.ac.tees.mad.eventspot.ui.screen.splash.SplashScreen
import uk.ac.tees.mad.eventspot.utils.Constants
import java.net.URLDecoder

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val homeViewModel:HomeViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Constants.SPLASH_SCREEN){
        composable(Constants.SPLASH_SCREEN){
            SplashScreen(navController)
        }

        composable(Constants.HOME_SCREEN) {
            HomeScreen(homeViewModel, navController)
        }

        composable(Constants.DETAILS_SCREEN+"/{event}",
            arguments = listOf(navArgument("event") { type = NavType.StringType })) { backStackEntry ->
                val jsonEvent = backStackEntry.arguments?.getString("event") ?: ""
                val decodedJson = URLDecoder.decode(jsonEvent, "UTF-8")
                val event = Event.fromJson(decodedJson)
            DetailsScreen(event)
        }

        composable(Constants.FAVORITE_SCREEN) {
            FavoriteScreen()
        }
    }
}