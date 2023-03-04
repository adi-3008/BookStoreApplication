package com.example.myapplication.model

data class Book(
    val title: String,
    val author: String,
    val image_url: String,
    val pages: Int,
    val isbn: String,
    val year_published: Int,
    val publisher: String,
    val subject: String,
    val genres: String,
    val originalLanguage: String,
    val description: String
)