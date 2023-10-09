package com.faroh.bwabanksandroid.view.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faroh.bwabanksandroid.core.data.Resource
import com.faroh.bwabanksandroid.core.domain.model.LoginBody
import com.faroh.bwabanksandroid.core.domain.model.UserModel
import com.faroh.bwabanksandroid.core.domain.usecase.BanksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val useCase: BanksUseCase) : ViewModel() {

    private val _signInState =
        MutableStateFlow<Resource<UserModel>?>(null)

    val signInState: StateFlow<Resource<UserModel>?> = _signInState

    fun loginUser(loginBody: LoginBody) {
        viewModelScope.launch {
            useCase.loginUser(
                loginBody
            ).collect { result ->
                delay(1000)
                _signInState.value = result
            }
        }
    }
}