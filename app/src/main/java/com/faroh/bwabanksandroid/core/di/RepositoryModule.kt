package com.faroh.bwabanksandroid.core.di

import com.faroh.bwabanksandroid.core.data.BanksRepository
import com.faroh.bwabanksandroid.core.domain.repository.IBanksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(banksRepository: BanksRepository): IBanksRepository

}