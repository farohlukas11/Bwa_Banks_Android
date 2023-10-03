package com.faroh.bwabanksandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.faroh.bwabanksandroid.ui.navigation.Screen
import com.faroh.bwabanksandroid.ui.theme.BwaBanksAndroidTheme
import com.faroh.bwabanksandroid.view.signin.SignInActivity
import com.faroh.bwabanksandroid.view.signup.SignUpActivity
import com.faroh.bwabanksandroid.view.splash.SplashActivity
import com.faroh.bwabanksandroid.view.splash.SplashEvent
import com.faroh.bwabanksandroid.view.splash.SplashState
import com.faroh.bwabanksandroid.view.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BwaBanksAndroidApps : ComponentActivity() {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashViewModel.onSplashInitial(SplashEvent.OnSplashInitial)

        setContent {
            BwaBanksAndroidTheme {
                Surface {
                    val navController = rememberNavController()
                    SplashActivity()

                    splashViewModel.userState.collectAsState(initial = SplashState.Unknown).value.let {
                        when (it) {
                            is SplashState.Authenticated -> {

                            }

                            is SplashState.Unauthenticated -> {

                            }

                            else -> {}
                        }

                        NavHost(
                            navController = navController,
                            startDestination = Screen.Splash.route
                        ) {
                            navigation(startDestination = Screen.Splash.route, route = "auth") {
                                composable(Screen.Splash.route) {
                                    SplashActivity()
                                }
                                composable(Screen.SignIn.route) {
                                    SignInActivity()
                                }
                                composable(Screen.SignUp.route) {
                                    SignUpActivity()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}