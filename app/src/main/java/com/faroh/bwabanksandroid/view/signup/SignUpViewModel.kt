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
    private val _signUpState: MutableStateFlow<SignUpState> =
        MutableStateFlow(SignUpState.SignUpEmpty)

    val signUpState: StateFlow<SignUpState>
        get() = _signUpState

}