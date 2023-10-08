package com.faroh.bwabanksandroid.view.signin

sealed class SignInState {
    data class LoginState(
        var email: String? = "",
        var password: String? = "",
    ) : SignInState()

    object FieldHasNull : SignInState()
    object LoginSuccess : SignInState()
    data class LoginError(val errorMessage: String) : SignInState()
    object LoginEmpty : SignInState()
    object LoginLoading : SignInState()
}