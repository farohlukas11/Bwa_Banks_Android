package com.faroh.bwabanksandroid.view.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.faroh.bwabanksandroid.core.data.Resource
import com.faroh.bwabanksandroid.core.domain.model.RegisterBody
import com.faroh.bwabanksandroid.core.domain.usecase.BanksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val useCase: BanksUseCase) : ViewModel() {

    private val registerState = mutableStateOf(SignUpState.RegisterState())

    private val _signUpState: MutableStateFlow<SignUpState> =
        MutableStateFlow(SignUpState.SignUpEmpty)

    val signUpState: StateFlow<SignUpState>
        get() = _signUpState

    private val checkEmailFilled =
        registerState.value.fullName != null || registerState.value.email != null || registerState.value.password != null

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.FullNameChanged -> registerState.value.copy(fullName = event.fullName)
            is SignUpEvent.EmailChanged -> registerState.value.copy(email = event.email)
            is SignUpEvent.PasswordChanged -> registerState.value.copy(password = event.password)
            is SignUpEvent.PinChanged -> registerState.value.copy(pin = event.pin)
            is SignUpEvent.PictureChanged -> registerState.value.copy(profilePicture = event.picture)
            is SignUpEvent.ktpChanged -> registerState.value.copy(ktp = event.ktp)
            is SignUpEvent.OnSignUpCheckEmailEvent -> {
                _signUpState.value = SignUpState.SignUpLoading
                if (checkEmailFilled) checkEmailUser()
                else _signUpState.value = SignUpState.FieldHasNull
            }

            is SignUpEvent.OnSignUpEvent -> {
                _signUpState.value = SignUpState.SignUpLoading
                if (checkEmailFilled || registerState.value.pin != null) registerUser()
                else _signUpState.value = SignUpState.FieldHasNull
            }
        }
    }

    private fun checkEmailUser() {
        when (val result = useCase.checkUser(registerState.value.email!!).toLiveData().value) {
            is Resource.Success -> _signUpState.value =
                if (!result.data!!) SignUpState.SignUpSuccess else SignUpState.SignUpEmpty

            is Resource.Error -> _signUpState.value =
                SignUpState.SignUpError(result.message.toString())

            else -> _signUpState.value = SignUpState.SignUpLoading
        }
    }

    private fun registerUser() {
        val result = useCase.registerUser(
            RegisterBody(
                name = registerState.value.fullName!!,
                email = registerState.value.email!!,
                password = registerState.value.password!!,
                pin = registerState.value.pin!!,
                profilePicture = registerState.value.profilePicture!!,
                ktp = registerState.value.ktp!!,
            )
        ).toLiveData().value

        when (result) {
            is Resource.Success -> _signUpState.value = if (result.data != null) {
                SignUpState.SignUpSuccess
            } else SignUpState.SignUpEmpty

            is Resource.Error -> _signUpState.value =
                SignUpState.SignUpError(result.message.toString())

            else -> _signUpState.value = SignUpState.SignUpLoading
        }
    }
}