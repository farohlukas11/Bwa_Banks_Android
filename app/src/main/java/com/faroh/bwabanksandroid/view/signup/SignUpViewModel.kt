package com.faroh.bwabanksandroid.view.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import androidx.lifecycle.viewModelScope
import com.faroh.bwabanksandroid.core.data.Resource
import com.faroh.bwabanksandroid.core.domain.model.RegisterBody
import com.faroh.bwabanksandroid.core.domain.usecase.BanksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val useCase: BanksUseCase) : ViewModel() {
    private val _signUpState =
        MutableStateFlow<Resource<Boolean>?>(null)

    val signUpState: StateFlow<Resource<Boolean>?> = _signUpState

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage: SharedFlow<String> = _toastMessage.asSharedFlow()

    fun checkUser(email: String) {
        viewModelScope.launch {
            useCase.checkUser(email)
                .collect { result ->
                    delay(1000)
                    _signUpState.value = result
                }
        }
    }

    fun setMessage(message: String) {
        viewModelScope.launch {
            _toastMessage.emit(message)
        }
    }
}