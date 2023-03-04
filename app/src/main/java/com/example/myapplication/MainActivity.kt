package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.api.Data
import com.example.myapplication.api.DataSourceInterface
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.repository.BookRepository
import com.example.myapplication.viewmodel.BookViewModel
import com.example.myapplication.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null

    lateinit var bookViewModel: BookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val adapter = MainAdapter()
        binding?.taskRV?.adapter = adapter

        val data : DataSourceInterface = Data()

        val bookRepository = BookRepository(data)

        bookViewModel = ViewModelProvider(this, ViewModelFactory(bookRepository))[BookViewModel::class.java]

        bookViewModel.bookList.observe(this) { books ->
            adapter.bookList = books
            adapter.notifyDataSetChanged()
        }

        bookViewModel.getBooks()

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}