package com.faroh.bwabanksandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import com.faroh.bwabanksandroid.ui.navigation.AuthNavigation
import com.faroh.bwabanksandroid.ui.theme.BwaBanksAndroidTheme
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

        splashViewModel.onEvent(SplashEvent.OnSplashInitial)

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
                                AuthNavigation(navController = navController)
                            }

                            else -> {
                                AuthNavigation(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}