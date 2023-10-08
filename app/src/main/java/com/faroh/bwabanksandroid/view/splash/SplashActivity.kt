package com.faroh.bwabanksandroid.view.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.faroh.bwabanksandroid.R
import com.faroh.bwabanksandroid.ui.theme.BwaBanksAndroidTheme
import com.faroh.bwabanksandroid.ui.theme.darkBackgroundColor
import com.faroh.bwabanksandroid.ui.theme.lightBackgroundColor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@Composable
fun SplashActivity(
    splashViewModel: SplashViewModel,
    toHome: () -> Unit,
    toSignIn: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize(), color = darkBackgroundColor) {
        SplashContent()
        splashViewModel.onEvent(SplashEvent.OnSplashInitial)

        splashViewModel.userState.collectAsState().value.let {
            LaunchedEffect(Unit) {
                delay(2000)
                when (it) {
                    is SplashState.Authenticated -> toHome.invoke()
                    is SplashState.Unauthenticated -> toSignIn.invoke()
                    is SplashState.Unknown -> toSignIn.invoke()
                }
            }
        }
    }
}

@Composable
fun SplashContent() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color.Transparent
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_logo_dark),
            contentDescription = "Logo Light",
            Modifier.width(155.dp)
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun SplashContentPreview() {
    BwaBanksAndroidTheme {
        Surface(color = darkBackgroundColor) {
            SplashContent()
        }
    }
}
