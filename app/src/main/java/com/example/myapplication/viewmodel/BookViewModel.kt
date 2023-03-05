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

    init {
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
