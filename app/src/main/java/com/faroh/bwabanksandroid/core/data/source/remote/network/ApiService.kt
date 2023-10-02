package com.faroh.bwabanksandroid.core.data.source.remote.network

import com.faroh.bwabanksandroid.core.data.source.remote.response.CheckUserResponse
import com.faroh.bwabanksandroid.core.data.source.remote.response.UserLogoutResponse
import com.faroh.bwabanksandroid.core.data.source.remote.response.UserResponse
import com.faroh.bwabanksandroid.core.domain.model.LoginBody
import com.faroh.bwabanksandroid.core.domain.model.RegisterBody
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("register")
    fun registerUser(
        @Body registerBody: RegisterBody
    ): Flowable<UserResponse>

    @POST("login")
    fun loginUser(
        @Body loginBody: LoginBody
    ): Flowable<UserResponse>

    @POST("logout")
    fun logoutUser(
        @Header("Authorization") token: String
    ): Flowable<UserLogoutResponse>

    @POST("is-email-exist")
    fun checkEmail(
        @Body email: String
    ): Flowable<CheckUserResponse>

}