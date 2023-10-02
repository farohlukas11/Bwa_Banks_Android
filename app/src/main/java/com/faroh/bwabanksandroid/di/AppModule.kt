package com.faroh.bwabanksandroid.di

import com.faroh.bwabanksandroid.core.domain.usecase.BanksInteractor
import com.faroh.bwabanksandroid.core.domain.usecase.BanksUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideBanksUseCase(banksInteractor: BanksInteractor): BanksUseCase
}