package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Book
import com.example.myapplication.repository.BookRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class BookViewModel (private val bookRepository: BookRepository): ViewModel() {

    val bookList: LiveData<List<Book>> get() = bookRepository.bookList

    internal fun getBooks() {

        // instead of using dependency injection for view-model to maintain single instance of the view-model
        // we can simply add a caching mechanism as bellow using singleton class

//        val cachedBooks = DataCache.getBooks()
//        cachedBooks?.let {
//            _bookList.value = it
//            return
//        }
        viewModelScope.launch {
            try {
                bookRepository.getBooks()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}

//object DataCache {
//    private var bookList: List<Book>? = null
//
//    fun getBooks(): List<Book>? {
//        return bookList
//    }
//
//    fun setBooks(books: List<Book>) {
//        bookList = books
//    }
//}