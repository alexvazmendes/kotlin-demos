package com.example.demo.core.port.`in`

import com.example.demo.core.domain.Book

interface SaveBookPortIn {
    fun execute(book: Input): Output

    class Input(val title: String,
                val author: String,
                val year: Int,
                val publisher: String)

    class Output(val id: String)
}