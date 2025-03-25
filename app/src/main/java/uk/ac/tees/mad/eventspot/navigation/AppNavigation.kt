package uk.ac.tees.mad.eventspot.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.eventspot.ui.screen.home.HomeScreen
import uk.ac.tees.mad.eventspot.ui.screen.home.HomeViewModel
import uk.ac.tees.mad.eventspot.ui.screen.splash.SplashScreen
import uk.ac.tees.mad.eventspot.utils.Constants

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val homeViewModel:HomeViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Constants.SPLASH_SCREEN){
        composable(Constants.SPLASH_SCREEN){
            SplashScreen(navController)
        }

        composable(Constants.HOME_SCREEN) {
            HomeScreen(homeViewModel)
        }
    }
}