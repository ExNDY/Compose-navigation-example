package app.thirtyninth.compose.navigation.example.screenWithOptionalParams

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import app.thirtyninth.compose.navigation.example.R.string
import app.thirtyninth.compose.navigation.example.screens.DefaultScreenNameExtensions.defaultScreenNameWithOptionalParams
import app.thirtyninth.compose.navigation.example.screens.Screen
import app.thirtyninth.compose.navigation.example.screens.ScreenNameExtensions.screenNameWithOptionalParams

object ScreenWithOptionalParams : Screen {
    private const val OPTIONAL_PARAM = "OPTIONAL_PARAM"

    override val navArgs: List<NamedNavArgument> = listOf(
        navArgument(OPTIONAL_PARAM) {
            type = NavType.StringType
            nullable = true
            defaultValue = null
        },
    )

    override val screenName: String = defaultScreenNameWithOptionalParams(
        listOf(OPTIONAL_PARAM)
    )

    fun screenNameWithoutArgument() = screenNameWithOptionalParams(emptyList())

    fun screenNameWithArgument(
        argument: String,
    ) = screenNameWithOptionalParams(
        listOf(OPTIONAL_PARAM to argument)
    )

    @Composable
    override fun Content(navController: NavController, args: Bundle?) {
        val argument: String = args?.getString(OPTIONAL_PARAM) ?: ":OPTIONAL_IS_EMPTY:"

        Box(modifier = Modifier.fillMaxSize()) {
            IconButton(
                modifier = Modifier
                    .statusBarsPadding()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Transparent),
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(imageVector = Filled.ArrowBackIosNew, contentDescription = null)
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .align(Alignment.Center),
                text = stringResource(
                    id = string.screen_with_o_param_message,
                    argument,
                ),
                fontSize = 16.sp,
                fontWeight = FontWeight.W700
            )
        }
    }
}
