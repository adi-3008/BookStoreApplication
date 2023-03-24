package com.example.myapplication.di

import com.example.myapplication.api.Data
import com.example.myapplication.api.DataSourceInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {
    @Binds
    abstract fun provideDataSource(data: Data) : DataSourceInterface
}