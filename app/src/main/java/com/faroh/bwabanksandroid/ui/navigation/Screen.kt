package com.faroh.bwabanksandroid.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object SignIn : Screen("signin")
    object SignUp : Screen("signup")
    object Home : Screen("home")
    object UploadPicture : Screen("upload-picture")
    object UploadKtp: Screen("Uupload-ktp")
}
