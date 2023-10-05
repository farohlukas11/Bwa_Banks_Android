package com.faroh.bwabanksandroid.view.signup
sealed class SignUpState {
    data class RegisterState(
        var fullName: String? = null,
        var email: String? = null,
        var password: String? = null,
        var pin: Int? = null,
        var profilePicture: String? = null,
        var ktp: String? = null,
    ) : SignUpState()

    object FieldHasNull : SignUpState()
    object SignUpSuccess : SignUpState()
    data class SignUpError(val errorMessage: String) : SignUpState()
    object SignUpEmpty : SignUpState()
    object SignUpLoading : SignUpState()
}
