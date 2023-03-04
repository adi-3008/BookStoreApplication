package com.example.myapplication.api

import com.example.myapplication.model.Book

interface DataSourceInterface{
    suspend fun getBooks() : List<Book>
}

