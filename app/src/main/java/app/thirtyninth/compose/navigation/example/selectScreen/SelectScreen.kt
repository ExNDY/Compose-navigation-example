package app.thirtyninth.compose.navigation.example.selectScreen

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.thirtyninth.compose.navigation.example.R
import app.thirtyninth.compose.navigation.example.defaultScreen.DefaultScreen
import app.thirtyninth.compose.navigation.example.screenWithOptionalParams.ScreenWithOptionalParams
import app.thirtyninth.compose.navigation.example.screenWithParams.ScreenWithParams
import app.thirtyninth.compose.navigation.example.screenWithRequiredParams.ScreenWithRequiredParams
import app.thirtyninth.compose.navigation.example.screens.DefaultScreenNameExtensions.defaultScreenName
import app.thirtyninth.compose.navigation.example.screens.Screen

object SelectScreen : Screen {
    override val screenName: String = defaultScreenName()

    @Composable
    override fun Content(navController: NavController, args: Bundle?) {
        val context = LocalContext.current

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(0.8f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val requiredValue: MutableState<String> = remember {
                    mutableStateOf("Required")
                }
                val optionalValue: MutableState<String> = remember {
                    mutableStateOf("Optional 1")
                }

                Text(text = stringResource(id = R.string.field_required_param))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = requiredValue.value,
                    onValueChange = { newText ->
                        requiredValue.value = newText
                    },
                    maxLines = 1
                )

                Text(text = stringResource(id = R.string.field_optional_param1))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = optionalValue.value,
                        onValueChange = { newText ->
                            optionalValue.value = newText
                        },
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        modifier = Modifier.size(56.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        ),
                        onClick = {
                            optionalValue.value = ""
                        }
                    ) {
                        Icon(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = null
                        )
                    }
                }

                NavigationButton(textResId = R.string.navigate_to_default) {
                    navController.navigate(
                        DefaultScreen.screenName
                    )
                }

                NavigationButton(
                    textResId = R.string.navigate_to_screen_with_required_params
                ) {
                    navController.navigate(
                        ScreenWithRequiredParams.screenName(
                            argument = requiredValue.value
                        )
                    )
                }

                NavigationButton(
                    textResId = R.string.navigate_to_screen_with_optional_params
                ) {
                    val inputFieldValue: String? = optionalValue.value.ifEmpty { null }

                    navController.navigate(
                        when (inputFieldValue == null) {
                            true -> ScreenWithOptionalParams.screenNameWithoutArgument()
                            false -> ScreenWithOptionalParams.screenNameWithArgument(
                                argument = inputFieldValue
                            )
                        }
                    )
                }

                NavigationButton(
                    textResId = R.string.navigate_to_screen_with_required_and_optional_params
                ) {
                    val requiredFieldValue: String? = requiredValue.value.ifEmpty { null }
                    val optionalFieldValue: String? = optionalValue.value.ifEmpty { null }

                    if (requiredFieldValue == null) {
                        Toast.makeText(
                            context,
                            "Please, input required field value",
                            Toast.LENGTH_SHORT
                        ).show()

                        return@NavigationButton
                    }

                    navController.navigate(
                        when (optionalFieldValue == null) {
                            true -> ScreenWithParams.screenNameWithRequiredArgument(
                                argument = requiredFieldValue
                            )
                            false -> ScreenWithParams.screenNameWithArguments(
                                argument = requiredFieldValue,
                                optionalArgument = optionalFieldValue
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun NavigationButton(
    @StringRes textResId: Int,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Text(
            text = stringResource(id = textResId),
            textAlign = TextAlign.Center
        )
    }
}