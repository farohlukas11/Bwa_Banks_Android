package com.faroh.bwabanksandroid.core.data

import com.faroh.bwabanksandroid.core.data.source.remote.RemoteDataSource
import com.faroh.bwabanksandroid.core.data.source.remote.response.ApiResponse
import com.faroh.bwabanksandroid.core.domain.model.LoginBody
import com.faroh.bwabanksandroid.core.domain.model.RegisterBody
import com.faroh.bwabanksandroid.core.domain.model.UserModel
import com.faroh.bwabanksandroid.core.domain.repository.IBanksRepository
import com.faroh.bwabanksandroid.core.utils.DataMapper
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BanksRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    IBanksRepository {
    override fun registerUser(registerBody: RegisterBody): Flowable<Resource<UserModel>> {
        val result = PublishSubject.create<Resource<UserModel>>()
        val compositeDisposable = CompositeDisposable()

        result.onNext(Resource.Loading())
        val register = remoteDataSource.userRegister(registerBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                compositeDisposable.dispose()
            }
            .subscribe {
                when (it) {
                    is ApiResponse.Success -> result.onNext(
                        Resource.Success(
                            DataMapper.mapUserResponseToModel(
                                it.data
                            )
                        )
                    )

                    is ApiResponse.Empty -> result.onNext(Resource.Success(UserModel()))
                    is ApiResponse.Error -> result.onNext(Resource.Error(it.errorMessage))
                }
            }

        compositeDisposable.add(register)
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    override fun loginUser(loginBody: LoginBody): Flowable<Resource<UserModel>> {
        val result = PublishSubject.create<Resource<UserModel>>()
        val compositeDisposable = CompositeDisposable()

        result.onNext(Resource.Loading())
        val login = remoteDataSource.userLogin(loginBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                compositeDisposable.dispose()
            }
            .subscribe {
                when (it) {
                    is ApiResponse.Success -> result.onNext(
                        Resource.Success(
                            DataMapper.mapUserResponseToModel(
                                it.data
                            )
                        )
                    )

                    is ApiResponse.Empty -> result.onNext(Resource.Success(UserModel()))
                    is ApiResponse.Error -> result.onNext(Resource.Error(it.errorMessage))
                }
            }

        compositeDisposable.add(login)
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    override fun logoutUser(token: String): Flowable<Resource<String>> {
        val result = PublishSubject.create<Resource<String>>()
        val compositeDisposable = CompositeDisposable()

        result.onNext(Resource.Loading())
        val logout = remoteDataSource.userLogout(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                compositeDisposable.dispose()
            }
            .subscribe {
                when (it) {
                    is ApiResponse.Success -> result.onNext(Resource.Success(it.data.message!!))
                    is ApiResponse.Empty -> result.onNext(Resource.Success(""))
                    is ApiResponse.Error -> result.onNext(Resource.Error(it.errorMessage))
                }
            }

        compositeDisposable.add(logout)
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    override fun checkUser(email: String): Flowable<Resource<Boolean>> {
        val result = PublishSubject.create<Resource<Boolean>>()
        val compositeDisposable = CompositeDisposable()

        result.onNext(Resource.Loading())
        val checkUser = remoteDataSource.checkUser(email)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                compositeDisposable.dispose()
            }
            .subscribe {
                when (it) {
                    is ApiResponse.Success -> result.onNext(Resource.Success(it.data.isEmailExist!!))
                    is ApiResponse.Empty -> result.onNext(Resource.Success(false))
                    is ApiResponse.Error -> result.onNext(Resource.Error(it.errorMessage))
                }
            }

        compositeDisposable.add(checkUser)
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }
}