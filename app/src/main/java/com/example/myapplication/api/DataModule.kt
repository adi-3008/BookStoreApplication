package com.example.myapplication.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DataModule {
    @Provides
    fun provideDataSource() : DataSourceInterface{
        return Data()
    }
}