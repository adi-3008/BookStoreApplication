package com.example.myapplication.di

import com.example.myapplication.api.Data
import com.example.myapplication.api.DataSourceInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun provideDataSource(data: Data) : DataSourceInterface
}