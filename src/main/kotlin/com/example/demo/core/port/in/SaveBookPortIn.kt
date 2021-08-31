package com.example.demo.core.port.`in`

import com.example.demo.core.domain.Book

interface SaveBookPortIn {
    fun execute(book: Book): String
}