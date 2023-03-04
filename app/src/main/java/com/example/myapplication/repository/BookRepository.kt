package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.api.DataSourceInterface
import com.example.myapplication.model.Book

class BookRepository(private var dataSource: DataSourceInterface){

    private var _bookList: MutableLiveData<List<Book>> = MutableLiveData()
    val bookList: LiveData<List<Book>> get() = _bookList

    suspend fun getBooks(){
        val cachedBooks = DataCache.getBooks()
        cachedBooks?.let {
            _bookList.value = it
            return
        }
        _bookList.value = dataSource.getBooks()
        _bookList.value?.let {
            DataCache.setBooks(it)
        }
    }
}

object DataCache {
    private var bookList: List<Book>? = null

    fun getBooks(): List<Book>? {
        return bookList
    }

    fun setBooks(books: List<Book>) {
        bookList = books
    }
}

