package com.example.finalapp.di

import com.example.finalapp.data.repository.AppRepository
import com.example.finalapp.domain.repository.AppRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindRepository(
        impl: AppRepository
    ): AppRepositoryInterface
}
