package com.faroh.bwabanksandroid.core.domain.usecase

import com.faroh.bwabanksandroid.core.data.Resource
import com.faroh.bwabanksandroid.core.domain.model.LoginBody
import com.faroh.bwabanksandroid.core.domain.model.RegisterBody
import com.faroh.bwabanksandroid.core.domain.model.UserModel
import com.faroh.bwabanksandroid.core.domain.repository.IBanksRepository
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BanksInteractor @Inject constructor(private val banksRepository: IBanksRepository) :
    BanksUseCase {
    override fun registerUser(registerBody: RegisterBody): Flow<Resource<UserModel>> {
        return banksRepository.registerUser(registerBody)
    }

    override fun loginUser(loginBody: LoginBody): Flow<Resource<UserModel>> {
        return banksRepository.loginUser(loginBody)
    }

    override fun logoutUser(token: String): Flow<Resource<String>> {
        return banksRepository.logoutUser(token)
    }

    override fun checkUser(email: String): Flow<Resource<Boolean>> {
        return banksRepository.checkUser(email)
    }
}