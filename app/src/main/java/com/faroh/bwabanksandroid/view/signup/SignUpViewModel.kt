package com.faroh.bwabanksandroid.view.signup

import androidx.lifecycle.ViewModel
import com.faroh.bwabanksandroid.core.domain.usecase.BanksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val useCase: BanksUseCase) : ViewModel() {
}