package com.faroh.bwabanksandroid.core.data.source.remote.network

import com.faroh.bwabanksandroid.core.data.source.remote.response.CheckUserResponse
import com.faroh.bwabanksandroid.core.data.source.remote.response.UserLogoutResponse
import com.faroh.bwabanksandroid.core.data.source.remote.response.UserResponse
import com.faroh.bwabanksandroid.core.domain.model.LoginBody
import com.faroh.bwabanksandroid.core.domain.model.RegisterBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("register")
    suspend fun registerUser(
        @Body registerBody: RegisterBody
    ): UserResponse?

    @POST("login")
    suspend fun loginUser(
        @Body loginBody: LoginBody
    ): UserResponse?

    @POST("logout")
    suspend fun logoutUser(
        @Header("Authorization") token: String
    ): UserLogoutResponse?

    @POST("is-email-exist")
    suspend fun checkEmail(
        @Body email: String
    ): CheckUserResponse?
}