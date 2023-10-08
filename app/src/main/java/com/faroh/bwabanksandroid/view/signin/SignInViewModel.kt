package com.faroh.bwabanksandroid.view.signin

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import androidx.lifecycle.viewModelScope
import com.faroh.bwabanksandroid.core.data.Resource
import com.faroh.bwabanksandroid.core.domain.model.LoginBody
import com.faroh.bwabanksandroid.core.domain.model.UserModel
import com.faroh.bwabanksandroid.core.domain.usecase.BanksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val useCase: BanksUseCase) : ViewModel() {

    private var loginState = mutableStateOf(SignInState.LoginState())

    private val _signInState: MutableStateFlow<SignInState> =
        MutableStateFlow(SignInState.LoginLoading)

    val signInState: StateFlow<SignInState>
        get() = _signInState

    fun onEvent(event: SignInEvent) {
        viewModelScope.launch {
            when (event) {
                is SignInEvent.EmailChanged -> loginState.value =
                    loginState.value.copy(email = event.email)

                is SignInEvent.PasswordChanged -> loginState.value =
                    loginState.value.copy(password = event.password)

                is SignInEvent.OnSignInEvent -> {
                    Log.d(
                        "login",
                        "${loginState.value}"
                    )

                    _signInState.value = SignInState.LoginLoading
                    if (loginState.value.email != null || loginState.value.password != null) {
//                        loginUser()
                    } else {
                        _signInState.value = SignInState.FieldHasNull
                    }

                }
            }
        }
    }

//    private fun loginUser() = viewModelScope.launch {
//        withContext(Dispatchers.IO) {
//            useCase.loginUser(
//                LoginBody(
//                    email = loginState.value.email!!,
//                    password = loginState.value.password!!
//                )
//            ).collect()
//        }
//    }
//
//    private suspend fun handleResponse(value: Resource<UserModel>) = withContext(Dispatchers.Main) {
//        when (value) {
//            is Resource.Success -> _signInState.value = if (value.data != null) {
//                SignInState.LoginSuccess
//            } else SignInState.LoginEmpty
//
//            is Resource.Error -> _signInState.value =
//                SignInState.LoginError(result.message.toString())
//
//            else -> _signInState.value = SignInState.LoginLoading
//        }
//    }
}