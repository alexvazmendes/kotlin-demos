package com.example.demo.adapter.web.dto

data class BookRequestDTO (val title: String,
                          val author: String,
                          val year: Int,
                          val publisher: String)

data class BookResponseDTO (val id: String,
                       val title: String)

data class GetBookResponseDTO (val id: String,
                               val title: String,
                               val author: String,
                               val year: Int,
                               val publisher: String,
                               val createdAt: String)