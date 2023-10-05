package com.faroh.bwabanksandroid.view.signin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.faroh.bwabanksandroid.core.data.Resource
import com.faroh.bwabanksandroid.core.domain.model.LoginBody
import com.faroh.bwabanksandroid.core.domain.usecase.BanksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val useCase: BanksUseCase) : ViewModel() {

    private var loginState = mutableStateOf(SignInState.LoginState())

    private val _signInState: MutableStateFlow<SignInState> =
        MutableStateFlow(SignInState.LoginEmpty)

    val signInState: StateFlow<SignInState>
        get() = _signInState

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.EmailChanged -> loginState.value.copy(email = event.email)
            is SignInEvent.PasswordChanged -> loginState.value.copy(password = event.password)
            is SignInEvent.OnSignInEvent -> {
                _signInState.value = SignInState.LoginLoading
                if (loginState.value.email != null || loginState.value.password != null) {
                    loginUser()
                } else {
                    _signInState.value = SignInState.FieldHasNull
                }
            }
        }
    }

    private fun loginUser() {
        val result = useCase.loginUser(
            LoginBody(
                email = loginState.value.email!!,
                password = loginState.value.password!!
            )
        ).toLiveData().value

        when (result) {
            is Resource.Success -> _signInState.value = if (result.data != null) {
                SignInState.LoginSuccess
            } else SignInState.LoginEmpty

            is Resource.Error -> _signInState.value =
                SignInState.LoginError(result.message.toString())

            else -> _signInState.value = SignInState.LoginLoading
        }
    }
}