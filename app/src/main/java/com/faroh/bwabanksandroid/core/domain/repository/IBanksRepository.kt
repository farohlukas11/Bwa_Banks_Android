package com.faroh.bwabanksandroid.core.domain.repository

import com.faroh.bwabanksandroid.core.data.Resource
import com.faroh.bwabanksandroid.core.data.source.remote.response.CheckUserResponse
import com.faroh.bwabanksandroid.core.data.source.remote.response.UserLogoutResponse
import com.faroh.bwabanksandroid.core.domain.model.LoginBody
import com.faroh.bwabanksandroid.core.domain.model.RegisterBody
import com.faroh.bwabanksandroid.core.domain.model.UserModel
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

interface IBanksRepository {

    fun registerUser(registerBody: RegisterBody): Flow<Resource<UserModel>>

    fun loginUser(loginBody: LoginBody): Flow<Resource<UserModel>>

    fun logoutUser(token: String): Flow<Resource<String>>

    fun checkUser(email: String): Flow<Resource<Boolean>>
}