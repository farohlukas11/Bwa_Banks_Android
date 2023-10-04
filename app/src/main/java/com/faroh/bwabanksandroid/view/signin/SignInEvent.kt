package com.faroh.bwabanksandroid.view.signin

sealed class SignInEvent {
    data class EmailChanged(val email: String) : SignInEvent()

    data class PasswordChanged(val password: String) : SignInEvent()

    object OnSignInEvent : SignInEvent()
}