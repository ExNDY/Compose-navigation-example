package app.thirtyninth.compose.navigation.example.screens

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.thirtyninth.compose.navigation.example.screens.ScreenNameExtension.getScreenName
import app.thirtyninth.compose.navigation.example.selectScreen.SelectScreen

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RootContainer() {
    val navController: NavHostController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { padding ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                ),
            navController = navController,
            startDestination = getScreenName<SelectScreen>()
        ) {

            allScreens.forEach { screen ->
                composableScreen(screen, navController)
            }
        }
    }
}

private fun NavGraphBuilder.composableScreen(
    screen: Screen,
    navController: NavHostController,
) {
    composable(
        route = screen.screenName,
        arguments = screen.navArgs
    ) { backStackEntry ->

        screen.Content(
            navController = navController,
            args = backStackEntry.arguments
        )
    }
}