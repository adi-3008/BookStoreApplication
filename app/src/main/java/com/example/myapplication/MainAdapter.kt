package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.BookListBinding
import com.example.myapplication.model.Book

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var bookList: List<Book> = emptyList()

    inner class MainViewHolder(private val itemBinding: BookListBinding) : RecyclerView.ViewHolder(itemBinding.root){

        fun bindItem(book: Book){

            itemBinding.bookName.text = book.title
            itemBinding.authorName.text = book.author
            Glide.with(itemBinding.root.context)
                .load(book.image_url)
                .into(itemBinding.bookImg)

            itemBinding.root.setOnClickListener {
                val intent = Intent(itemBinding.root.context, BookActivity::class.java)
                intent.putExtra("pos", adapterPosition)
                itemBinding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(BookListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val book = bookList[position]
        holder.bindItem(book)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }
}