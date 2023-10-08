package com.faroh.bwabanksandroid.core.data.source.remote

import android.util.Log
import com.faroh.bwabanksandroid.core.data.source.remote.network.ApiService
import com.faroh.bwabanksandroid.core.data.source.remote.response.ApiResponse
import com.faroh.bwabanksandroid.core.data.source.remote.response.CheckUserResponse
import com.faroh.bwabanksandroid.core.data.source.remote.response.UserLogoutResponse
import com.faroh.bwabanksandroid.core.data.source.remote.response.UserResponse
import com.faroh.bwabanksandroid.core.domain.model.LoginBody
import com.faroh.bwabanksandroid.core.domain.model.RegisterBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun registerUser(registerBody: RegisterBody): Flow<ApiResponse<UserResponse>> = flow {
        try {
            val register = apiService.registerUser(registerBody)
            emit(if (register != null) ApiResponse.Success(register) else ApiResponse.Empty)
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
            Log.e(RemoteDataSource::class.java.simpleName, e.localizedMessage!!)
        }
    }

    suspend fun loginUser(loginBody: LoginBody): Flow<ApiResponse<UserResponse>> = flow {
        try {
            val login = apiService.loginUser(loginBody)
            emit(if (login != null) ApiResponse.Success(login) else ApiResponse.Empty)
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
            Log.e(RemoteDataSource::class.java.simpleName, e.localizedMessage!!)
        }
    }

    suspend fun logoutUser(token: String): Flow<ApiResponse<UserLogoutResponse>> = flow {
        try {
            val logout = apiService.logoutUser(token)
            emit(if (logout != null) ApiResponse.Success(logout) else ApiResponse.Empty)
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
            Log.e(RemoteDataSource::class.java.simpleName, e.localizedMessage!!)
        }
    }

    suspend fun checkEmailUser(email: String): Flow<ApiResponse<CheckUserResponse>> = flow {
        try {
            val checkEmail = apiService.checkEmail(email)
            emit(if (checkEmail != null) ApiResponse.Success(checkEmail) else ApiResponse.Empty)
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
            Log.e(RemoteDataSource::class.java.simpleName, e.localizedMessage!!)
        }
    }
}