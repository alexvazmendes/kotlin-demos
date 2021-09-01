package com.example.demo.adapter.web.dto

data class BookRequestDTO (val title: String,
                          val author: String,
                          val year: Int,
                          val publisher: String)

class BookResponseDTO (val id: String,
                       val title: String)