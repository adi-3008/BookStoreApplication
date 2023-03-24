package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityBookBinding
import com.example.myapplication.viewmodel.BookViewModel
import com.example.myapplication.viewmodel.ViewModelFactory

class BookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookBinding

    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book)

        val pos = intent.getIntExtra("pos", 0)

        val bookRepository = (application as MainApplication).bookRepository

        bookViewModel = ViewModelProvider(this, ViewModelFactory(bookRepository))[BookViewModel::class.java]

        binding.bookViewModel = bookViewModel
        binding.pos = pos
        binding.lifecycleOwner = this

        bookViewModel.bookList.observe(this){
            supportActionBar?.title = it[pos].title
        }

    }
}