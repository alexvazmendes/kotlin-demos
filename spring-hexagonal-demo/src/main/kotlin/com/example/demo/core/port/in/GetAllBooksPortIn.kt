package com.example.demo.core.port.`in`

interface GetAllBooksPortIn {
    fun execute(): List<GetBookByIdPortIn.Output>
}