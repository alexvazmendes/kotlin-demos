package com.example.demo.core.port.`in`

interface GetBookByIdPortIn {
    fun execute(id: String): Output

    data class Output (val id: String,
                       val title: String,
                       val author: String,
                       val year: Int,
                       val publisher: String,
                       val createdAt: String)
}