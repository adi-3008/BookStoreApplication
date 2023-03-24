package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.viewmodel.BookViewModel
import com.example.myapplication.viewmodel.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MainAdapter()
        binding.taskRV.adapter = adapter

        val bookRepository = (application as MainApplication).bookRepository

        bookViewModel = ViewModelProvider(this, ViewModelFactory(bookRepository))[BookViewModel::class.java]

        bookViewModel.bookList.observe(this) { books ->
            adapter.bookList = books
            adapter.notifyDataSetChanged()
        }
    }
}