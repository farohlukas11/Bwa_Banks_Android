package com.faroh.bwabanksandroid.view.signin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import androidx.lifecycle.viewModelScope
import com.faroh.bwabanksandroid.core.domain.model.LoginBody
import com.faroh.bwabanksandroid.core.domain.usecase.BanksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val useCase: BanksUseCase) : ViewModel() {

    private var loginState = mutableStateOf(SignInState.LoginState())

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.EmailChanged -> loginState.value.copy(email = event.email)
            is SignInEvent.PasswordChanged -> loginState.value.copy(password = event.password)
            is SignInEvent.OnSignInEvent -> {
                viewModelScope.launch {
                    val result = useCase.loginUser(
                        LoginBody(
                            email = loginState.value.email,
                            password = loginState.value.password
                        )
                    ).toLiveData()


                }
            }
        }
    }

}