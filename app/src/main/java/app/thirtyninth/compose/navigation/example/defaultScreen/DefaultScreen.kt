package app.thirtyninth.compose.navigation.example.defaultScreen

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.navigation.NavController
import app.thirtyninth.compose.navigation.example.R
import app.thirtyninth.compose.navigation.example.screens.DefaultScreenNameExtensions.defaultScreenName
import app.thirtyninth.compose.navigation.example.screens.Screen

object DefaultScreen : Screen {
    override val screenName: String = defaultScreenName()

    @Composable
    override fun Content(navController: NavController, args: Bundle?) {
        Box(modifier = Modifier.fillMaxSize()) {
            IconButton(
                modifier = Modifier
                    .statusBarsPadding()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Transparent),
                onClick = navController::popBackStack
            ) {
                Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .align(Alignment.Center),
                text = stringResource(id = R.string.default_screen_message),
                fontSize = 16.sp,
                fontWeight = FontWeight.W700
            )
        }
    }
}