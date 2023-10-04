package com.faroh.bwabanksandroid.view.signin

sealed class SignInState {
    data class LoginState(
        var email: String = "",
        var password: String = "",
    ) : SignInState()
}