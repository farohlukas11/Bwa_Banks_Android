package com.faroh.bwabanksandroid.view.splash

sealed class SplashState {
    object Authenticated : SplashState()

    object Unauthenticated : SplashState()

    object Unknown : SplashState()
}
