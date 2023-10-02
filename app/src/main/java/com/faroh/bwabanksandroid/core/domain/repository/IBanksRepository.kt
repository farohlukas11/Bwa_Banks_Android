package com.faroh.bwabanksandroid.core.domain.repository

import com.faroh.bwabanksandroid.core.data.Resource
import com.faroh.bwabanksandroid.core.data.source.remote.response.UserLogoutResponse
import com.faroh.bwabanksandroid.core.domain.model.LoginBody
import com.faroh.bwabanksandroid.core.domain.model.RegisterBody
import com.faroh.bwabanksandroid.core.domain.model.UserModel
import io.reactivex.Flowable

interface IBanksRepository {

    fun registerUser(registerBody: RegisterBody): Flowable<Resource<UserModel>>

    fun loginUser(loginBody: LoginBody): Flowable<Resource<UserModel>>

    fun logoutUser(token: String): Flowable<Resource<String>>

    fun checkUser(email: String): Flowable<Resource<Boolean>>
}