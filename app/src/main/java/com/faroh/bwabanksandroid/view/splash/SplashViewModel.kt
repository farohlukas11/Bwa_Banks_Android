package com.faroh.bwabanksandroid.view.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import androidx.lifecycle.viewModelScope
import com.faroh.bwabanksandroid.core.data.Resource
import com.faroh.bwabanksandroid.core.domain.usecase.BanksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val banksUseCase: BanksUseCase) : ViewModel() {

    private val _userState: MutableStateFlow<SplashState> =
        MutableStateFlow(SplashState.Unknown)

    val userState: StateFlow<SplashState>
        get() = _userState

    fun onEvent(event: SplashEvent) {
        when (event) {
            is SplashEvent.OnSplashInitial -> {
                viewModelScope.launch {
                    val result = banksUseCase.checkUser("").toLiveData().value
                    delay(2000)
                    when (result) {
                        is Resource.Success -> _userState.value = SplashState.Authenticated
                        is Resource.Loading -> _userState.value = SplashState.Unknown
                        is Resource.Error -> _userState.value = SplashState.Unauthenticated
                        else -> _userState.value = SplashState.Unauthenticated
                    }
                }
            }
        }
    }
}