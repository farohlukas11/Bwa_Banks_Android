package com.faroh.bwabanksandroid.core.data.source.remote

import android.util.Log
import com.faroh.bwabanksandroid.core.data.source.remote.network.ApiService
import com.faroh.bwabanksandroid.core.data.source.remote.response.ApiResponse
import com.faroh.bwabanksandroid.core.data.source.remote.response.CheckUserResponse
import com.faroh.bwabanksandroid.core.data.source.remote.response.UserLogoutResponse
import com.faroh.bwabanksandroid.core.data.source.remote.response.UserResponse
import com.faroh.bwabanksandroid.core.domain.model.LoginBody
import com.faroh.bwabanksandroid.core.domain.model.RegisterBody
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    fun userRegister(registerBody: RegisterBody): Flowable<ApiResponse<UserResponse>> {
        val result = PublishSubject.create<ApiResponse<UserResponse>>()
        val compositeDisposable = CompositeDisposable()

        val register = apiService.registerUser(registerBody)
            .observeOn(Schedulers.computation())
            .subscribeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                compositeDisposable.dispose()
            }
            .subscribe(
                { response ->
                    result.onNext(if (response != null) ApiResponse.Success(response) else ApiResponse.Empty)
                }, { error ->
                    result.onNext(ApiResponse.Error(error.message.toString()))
                    Log.e("USER REGISTER", error.toString())
                }
            )

        compositeDisposable.add(register)
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun userLogin(loginBody: LoginBody): Flowable<ApiResponse<UserResponse>> {
        val result = PublishSubject.create<ApiResponse<UserResponse>>()
        val compositeDisposable = CompositeDisposable()

        val login = apiService.loginUser(loginBody)
            .observeOn(Schedulers.computation())
            .subscribeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                compositeDisposable.dispose()
            }
            .subscribe(
                { response ->
                    result.onNext(if (response != null) ApiResponse.Success(response) else ApiResponse.Empty)
                }, { error ->
                    result.onNext(ApiResponse.Error(error.message.toString()))
                    Log.e("USER LOGIN", error.toString())
                }
            )

        compositeDisposable.add(login)
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun userLogout(token: String): Flowable<ApiResponse<UserLogoutResponse>> {
        val result = PublishSubject.create<ApiResponse<UserLogoutResponse>>()
        val compositeDisposable = CompositeDisposable()

        val logout = apiService.logoutUser(token)
            .observeOn(Schedulers.computation())
            .subscribeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                compositeDisposable.dispose()
            }
            .subscribe(
                { response ->
                    result.onNext(if (response != null) ApiResponse.Success(response) else ApiResponse.Empty)
                }, { error ->
                    result.onNext(ApiResponse.Error(error.message.toString()))
                    Log.e("USER LOGOUT", error.toString())
                }
            )

        compositeDisposable.add(logout)
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun checkUser(email: String): Flowable<ApiResponse<CheckUserResponse>> {
        val result = PublishSubject.create<ApiResponse<CheckUserResponse>>()
        val compositeDisposable = CompositeDisposable()

        val checkUser = apiService.checkEmail(email)
            .observeOn(Schedulers.computation())
            .subscribeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                compositeDisposable.dispose()
            }
            .subscribe(
                { response ->
                    result.onNext(if (response != null) ApiResponse.Success(response) else ApiResponse.Empty)
                }, { error ->
                    result.onNext(ApiResponse.Error(error.message.toString()))
                    Log.e("USER CHECK", error.toString())
                }
            )

        compositeDisposable.add(checkUser)
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }
}