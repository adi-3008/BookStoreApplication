package com.example.myapplication

import android.app.Application
import com.example.myapplication.repository.BookRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application() {
    @Inject
    lateinit var bookRepository: BookRepository
}