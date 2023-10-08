package com.faroh.bwabanksandroid.core.data

import com.faroh.bwabanksandroid.core.data.source.remote.RemoteDataSource
import com.faroh.bwabanksandroid.core.data.source.remote.response.ApiResponse
import com.faroh.bwabanksandroid.core.data.source.remote.response.CheckUserResponse
import com.faroh.bwabanksandroid.core.data.source.remote.response.UserLogoutResponse
import com.faroh.bwabanksandroid.core.data.source.remote.response.UserResponse
import com.faroh.bwabanksandroid.core.domain.model.LoginBody
import com.faroh.bwabanksandroid.core.domain.model.RegisterBody
import com.faroh.bwabanksandroid.core.domain.model.UserModel
import com.faroh.bwabanksandroid.core.domain.repository.IBanksRepository
import com.faroh.bwabanksandroid.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BanksRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    IBanksRepository {
    override fun registerUser(registerBody: RegisterBody): Flow<Resource<UserModel>> {
        return object : NetworkOnlyResource<UserModel, UserResponse>() {
            override fun loadFromNetwork(data: UserResponse): Flow<UserModel> {
                return DataMapper.mapUserResponseToModel(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<UserResponse>> {
                return remoteDataSource.registerUser(registerBody)
            }
        }.asFlow()
    }

    override fun loginUser(loginBody: LoginBody): Flow<Resource<UserModel>> {
        return object : NetworkOnlyResource<UserModel, UserResponse>() {
            override fun loadFromNetwork(data: UserResponse): Flow<UserModel> {
                return DataMapper.mapUserResponseToModel(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<UserResponse>> {
                return remoteDataSource.loginUser(loginBody)
            }
        }.asFlow()
    }

    override fun logoutUser(token: String): Flow<Resource<String>> {
        return object : NetworkOnlyResource<String, UserLogoutResponse>() {
            override fun loadFromNetwork(data: UserLogoutResponse): Flow<String> {
                return flowOf(data.message!!)
            }

            override suspend fun createCall(): Flow<ApiResponse<UserLogoutResponse>> {
                return remoteDataSource.logoutUser(token)
            }
        }.asFlow()
    }

    override fun checkUser(email: String): Flow<Resource<Boolean>> {
        return object : NetworkOnlyResource<Boolean, CheckUserResponse>() {
            override fun loadFromNetwork(data: CheckUserResponse): Flow<Boolean> {
                return flowOf(data.isEmailExist!!)
            }

            override suspend fun createCall(): Flow<ApiResponse<CheckUserResponse>> {
                return remoteDataSource.checkEmailUser(email)
            }
        }.asFlow()
    }
}