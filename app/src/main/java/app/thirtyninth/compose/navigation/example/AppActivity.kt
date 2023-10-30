package app.thirtyninth.compose.navigation.example

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import app.thirtyninth.compose.navigation.example.screens.RootContainer

class MainActivity : ComponentActivity() {
    @Suppress("UnnecessaryApply")
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            ),
        )

        super.onCreate(savedInstanceState)

        splashScreen.apply {
            setKeepOnScreenCondition {
                //TODO: Use for additional logic on start app (check version or auth token)
                false
            }
        }

        setContent {
            MaterialTheme {
                RootContainer()
            }
        }
    }
}
