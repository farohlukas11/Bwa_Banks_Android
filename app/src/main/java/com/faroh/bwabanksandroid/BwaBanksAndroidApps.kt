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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BwaBanksAndroidTheme {
                Surface {
                    val navController = rememberNavController()
                    AuthNavigation(navController = navController)
                }
            }
        }
    }
}