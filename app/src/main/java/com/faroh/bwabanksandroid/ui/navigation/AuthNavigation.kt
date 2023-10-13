package com.faroh.bwabanksandroid.ui.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.faroh.bwabanksandroid.view.home.HomeActivity
import com.faroh.bwabanksandroid.view.signin.SignInActivity
import com.faroh.bwabanksandroid.view.signup.SignUpActivity
import com.faroh.bwabanksandroid.view.signup.SuccessSignUpActivity
import com.faroh.bwabanksandroid.view.signup.UploadKtpActivity
import com.faroh.bwabanksandroid.view.signup.UploadPictureActivity
import com.faroh.bwabanksandroid.view.splash.SplashActivity
import com.google.gson.Gson

@Composable
fun AuthNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashActivity(splashViewModel = hiltViewModel(),
                toSignIn = {
                    navController.navigate(Screen.SignIn.route) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }
                    }
                }, toHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }
                    }
                })
        }
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
                    navController.currentBackStackEntry?.savedStateHandle?.apply {
                        set("reg_body", it)
                    }
                    navController.navigate(Screen.UploadPicture.route)
                }
            )
        }
        composable(Screen.UploadPicture.route) {
            UploadPictureActivity(
                navController = navController,
                signUpViewModel = hiltViewModel(),
                toUploadKtp = {
                    navController.navigate(Screen.UploadKtp.route) {
                        popUpTo(Screen.UploadPicture.route) {
                            inclusive = true
                        }
                    }
                })
        }
        composable(Screen.UploadKtp.route) {
            UploadKtpActivity(signUpViewModel = hiltViewModel()) {
                navController.navigate(Screen.UploadKtp.route) {
                    popUpTo(Screen.SuccessSignUp.route) {
                        inclusive = true
                    }
                }
            }
        }
        composable(Screen.SuccessSignUp.route) {
            SuccessSignUpActivity {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.SuccessSignUp.route) {
                        inclusive = true
                    }
                }
            }
        }
        composable(Screen.Home.route) {
            HomeActivity()
        }
    }
}