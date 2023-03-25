package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.api.DataSourceInterface
import com.example.myapplication.model.Book
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepository @Inject constructor(private var dataSource: DataSourceInterface){

    private var _bookList: MutableLiveData<List<Book>> = MutableLiveData()
    val bookList: LiveData<List<Book>> get() = _bookList

    suspend fun getBooks(){
        _bookList.value = dataSource.getBooks()
    }
}


