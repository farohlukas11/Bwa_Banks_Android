package com.faroh.bwabanksandroid.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.faroh.bwabanksandroid.view.signin.SignInActivity
import com.faroh.bwabanksandroid.view.signin.SignInViewModel
import com.faroh.bwabanksandroid.view.signup.SignUpActivity
import com.faroh.bwabanksandroid.view.signup.UploadKtpActivity
import com.faroh.bwabanksandroid.view.signup.UploadPictureActivity
import com.faroh.bwabanksandroid.view.splash.SplashActivity

@Composable
fun AuthNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SignIn.route
    ) {
        composable(Screen.SignIn.route) {
            SignInActivity(
                signInViewModel = hiltViewModel(),
                toSignUp = { navController.navigate(Screen.SignUp.route) },
                toHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.SignIn.route) {
                            inclusive = true
                        }
                    }
                },
            )
        }
        composable(Screen.SignUp.route) {
            SignUpActivity(
                signUpViewModel = hiltViewModel(),
                toSignIn = { navController.navigate(Screen.SignIn.route) },
                toUploadPic = {
                    navController.navigate(Screen.UploadPicture.route)
                }
            )
        }
        composable(Screen.UploadPicture.route) {
            UploadPictureActivity(signUpViewModel = hiltViewModel(), toUploadKtp = {
                navController.navigate(Screen.UploadKtp.route)
            })
        }
        composable(Screen.UploadKtp.route) {
            UploadKtpActivity(signUpViewModel = hiltViewModel()) {

            }
        }
    }
}