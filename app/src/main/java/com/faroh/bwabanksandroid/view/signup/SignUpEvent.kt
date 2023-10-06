package com.faroh.bwabanksandroid.view.signup

sealed class SignUpEvent {
    data class FullNameChanged(val fullName: String) : SignUpEvent()

    data class EmailChanged(val email: String) : SignUpEvent()

    data class PasswordChanged(val password: String) : SignUpEvent()

    data class PinChanged(val pin: Int) : SignUpEvent()

    data class PictureChanged(val picture: String?) : SignUpEvent()

    data class ktpChanged(val ktp: String?) : SignUpEvent()


    object OnSignUpCheckEmailEvent : SignUpEvent()

    object OnSignUpEvent : SignUpEvent()
}
