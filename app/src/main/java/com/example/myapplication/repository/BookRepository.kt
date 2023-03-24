package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.api.DataSourceInterface
import com.example.myapplication.model.Book
import javax.inject.Inject

class BookRepository @Inject constructor(private var dataSource: DataSourceInterface){

    private var _bookList: MutableLiveData<List<Book>> = MutableLiveData()
    val bookList: LiveData<List<Book>> get() = _bookList

    suspend fun getBooks(){
        if (DataCache.getBooks() != null){
            _bookList.value = DataCache.getBooks()
            return
        }
        _bookList.value = dataSource.getBooks()
        DataCache.setBooks(_bookList.value!!)
    }
}

object DataCache{
    private var bookList : List<Book>? = null

    fun setBooks(bookList: List<Book>){
        this.bookList = bookList
    }

    fun getBooks() : List<Book>?{
        return bookList
    }
}


