package app.thirtyninth.compose.navigation.example.screens

import app.thirtyninth.compose.navigation.example.defaultScreen.DefaultScreen
import app.thirtyninth.compose.navigation.example.screenWithOptionalParams.ScreenWithOptionalParams
import app.thirtyninth.compose.navigation.example.screenWithParams.ScreenWithParams
import app.thirtyninth.compose.navigation.example.screenWithRequiredParams.ScreenWithRequiredParams
import app.thirtyninth.compose.navigation.example.selectScreen.SelectScreen

val allScreens: List<Screen> = listOf(
    SelectScreen,
    DefaultScreen,
    ScreenWithRequiredParams,
    ScreenWithOptionalParams,
    ScreenWithParams,
)
